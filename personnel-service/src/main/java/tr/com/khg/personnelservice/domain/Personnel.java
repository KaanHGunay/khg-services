package tr.com.khg.personnelservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Personnel {

    private String name;

    private String surname;

    private String registry;

    private String job;

    private String place_of_birth;
}
