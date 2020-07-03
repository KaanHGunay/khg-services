package tr.com.khg.userservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import tr.com.khg.userservice.service.dto.UserDTO;

public interface UserEntityService extends UserDetailsService {

    UserDTO createUser(UserDTO userDetails);
    UserDTO getUserDetailsByEmail(String email);
    UserDTO getUserByUserId(String userId);

}
