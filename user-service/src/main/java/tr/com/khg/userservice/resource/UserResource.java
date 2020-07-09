package tr.com.khg.userservice.resource;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tr.com.khg.userservice.domain.ui.CreateUserRequestModel;
import tr.com.khg.userservice.domain.ui.CreateUserResponseModel;
import tr.com.khg.userservice.domain.ui.UserResponseModel;
import tr.com.khg.userservice.service.UserEntityService;
import tr.com.khg.userservice.service.dto.UserDTO;

@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserEntityService userEntityService;
    private final Environment environment;

    public UserResource(UserEntityService userEntityService, Environment environment) {
        this.userEntityService = userEntityService;
        this.environment = environment;
    }

    @GetMapping("/token")
    public String getToken() {
        return environment.getProperty("token.secret");
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
            produces = { /*MediaType.APPLICATION_XML_VALUE,*/ MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody CreateUserRequestModel userDetails)
    {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDTO userDto = modelMapper.map(userDetails, UserDTO.class);
        UserDTO createdUser = userEntityService.createUser(userDto);
        CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    // PreAuthorize metottan önce çalışır ve verilen kontrolleri yapar
    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @PreAuthorize("principal == #userId")  // Giriş yapan kullanıcı sadece kendi bilgilerini alabilsin
    public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId) {

        UserDTO userDTO = userEntityService.getUserByUserId(userId);
        UserResponseModel returnValue = new ModelMapper().map(userDTO, UserResponseModel.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    // PostAuthorize metottan sonra çalışır ve sonuç üzerinde kontroller yapar
    // PostAuthorize -> principal == returnObject.body().userId de olabilir
    @GetMapping(value = "/2/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @PostAuthorize("principal == returnObject.getBody().getUserId()")  // Giriş yapan kullanıcı sonuçta kendi bilgileri varsa alabilsin
    public ResponseEntity<UserResponseModel> getUser2(@PathVariable("userId") String userId) {

        UserDTO userDTO = userEntityService.getUserByUserId(userId);
        UserResponseModel returnValue = new ModelMapper().map(userDTO, UserResponseModel.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
