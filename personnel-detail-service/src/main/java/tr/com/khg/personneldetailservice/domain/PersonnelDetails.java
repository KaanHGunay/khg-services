package tr.com.khg.personneldetailservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "details", name = "personnel_details")
public class PersonnelDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String job;

    @Column(nullable = false)
    private String place_of_birth;

    @Column(nullable = false)
    private String registry;
}
