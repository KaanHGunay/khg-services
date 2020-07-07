package tr.com.khg.userservice.service.impl;

import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.khg.userservice.domain.UserEntity;
import tr.com.khg.userservice.domain.ui.AlbumsResponseModel;
import tr.com.khg.userservice.repository.UserEntityRepository;
import tr.com.khg.userservice.service.AlbumsServiceClient;
import tr.com.khg.userservice.service.UserEntityService;
import tr.com.khg.userservice.service.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserEntityRepository userEntityRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // private final RestTemplate restTemplate;

    private final Environment environment;

    private final AlbumsServiceClient albumsServiceClient;

    public UserEntityServiceImpl(UserEntityRepository userEntityRepository,
                                 BCryptPasswordEncoder bCryptPasswordEncoder,
                                 // RestTemplate restTemplate,
                                 Environment environment,
                                 AlbumsServiceClient albumsServiceClient) {
        this.userEntityRepository = userEntityRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        // this.restTemplate = restTemplate;
        this.environment = environment;
        this.albumsServiceClient = albumsServiceClient;
    }

    @Override
    public UserDTO createUser(UserDTO userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
        userEntityRepository.save(userEntity);

        return modelMapper.map(userEntity, UserDTO.class);
    }

    @Override
    public UserDTO getUserDetailsByEmail(String email) {
        UserEntity userEntity = userEntityRepository.findByEmail(email);

        if(userEntity == null) throw new UsernameNotFoundException(email);

        return new ModelMapper().map(userEntity, UserDTO.class);
    }

    @Override
    public UserDTO getUserByUserId(String userId) {
        UserEntity userEntity = userEntityRepository.findByUserId(userId);

        if(userEntity == null) throw new UsernameNotFoundException(userId);

        UserDTO userDTO = new ModelMapper().map(userEntity, UserDTO.class);

        /*
        ResponseEntity<List<AlbumsResponseModel>> albumListResponse =
            restTemplate.exchange(
                String.format(Objects.requireNonNull(environment.getProperty("albums.get")), userId),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AlbumsResponseModel>>() {}
            );

        List<AlbumsResponseModel> albumsList = albumListResponse.getBody();
         */

        List<AlbumsResponseModel> albumsList = null;
        albumsList = albumsServiceClient.getAlbums(userId);

        userDTO.setAlbumsList(albumsList);

        return userDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityRepository.findByEmail(username);

        if(userEntity == null) throw new UsernameNotFoundException(username);

        return new User(userEntity.getEmail(),
                userEntity.getEncryptedPassword(), true, true,
                true, true, new ArrayList<>());
    }
}
