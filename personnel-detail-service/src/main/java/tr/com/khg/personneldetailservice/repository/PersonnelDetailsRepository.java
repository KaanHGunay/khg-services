package tr.com.khg.personneldetailservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.khg.personneldetailservice.domain.PersonnelDetails;

public interface PersonnelDetailsRepository extends JpaRepository<PersonnelDetails, Long> {

    PersonnelDetails getByRegistry(String registry);
}
