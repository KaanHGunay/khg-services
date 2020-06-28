package tr.com.khg.userservice.domain.ui;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponseModel {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;

}
