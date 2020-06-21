package tr.com.khg.personnelservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelDetails {

    private Long id;

    private String job;

    private String place_of_birth;

    private String registry;
}
