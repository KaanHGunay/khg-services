package tr.com.khg.personnelservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelInfo {

    private Long id;

    private String name;

    private String surname;

    private String registry;
}
