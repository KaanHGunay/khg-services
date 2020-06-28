package tr.com.khg.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.khg.userservice.domain.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    UserEntity findByUserId(String userId);

}
