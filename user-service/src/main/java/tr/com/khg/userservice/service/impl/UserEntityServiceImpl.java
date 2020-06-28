package tr.com.khg.userservice.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.khg.userservice.domain.UserEntity;
import tr.com.khg.userservice.repository.UserEntityRepository;
import tr.com.khg.userservice.service.UserEntityService;
import tr.com.khg.userservice.service.dto.UserDTO;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    private final UserEntityRepository userEntityRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserEntityServiceImpl(UserEntityRepository userEntityRepository,
                                 BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityRepository.findByEmail(username);

        if(userEntity == null) throw new UsernameNotFoundException(username);

        return new User(userEntity.getEmail(),
                userEntity.getEncryptedPassword(), true, true,
                true, true, new ArrayList<>());
    }
}
