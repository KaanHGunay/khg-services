package tr.com.khg.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "user_database", name = "users")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -2731425678149216053L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=50, name = "FIRSTNAME")
    private String firstName;

    @Column(nullable=false, length=5, name = "LASTNAME")
    private String lastName;

    @Column(nullable=false, length=120, unique=true, name = "EMAIL")
    private String email;

    @Column(nullable=false, unique=true, name = "USERID")
    private String userId;

    @Column(nullable=false, unique=true, name = "ENCRYPTEDPASSWORD")
    private String encryptedPassword;
}
