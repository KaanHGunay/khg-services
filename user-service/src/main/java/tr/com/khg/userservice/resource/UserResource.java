package tr.com.khg.userservice.resource;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.khg.userservice.domain.ui.CreateUserRequestModel;
import tr.com.khg.userservice.domain.ui.CreateUserResponseModel;
import tr.com.khg.userservice.service.UserEntityService;
import tr.com.khg.userservice.service.dto.UserDTO;

@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserEntityService userEntityService;

    public UserResource(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
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
}
