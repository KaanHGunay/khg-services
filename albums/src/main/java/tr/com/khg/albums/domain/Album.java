package tr.com.khg.albums.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    private long id;
    private String albumId;
    private String userId;
    private String name;
    private String description;

}
