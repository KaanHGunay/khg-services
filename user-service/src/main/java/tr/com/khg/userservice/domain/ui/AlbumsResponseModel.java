package tr.com.khg.userservice.domain.ui;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumsResponseModel {

    private String albumId;
    private String userId;
    private String name;
    private String description;

}
