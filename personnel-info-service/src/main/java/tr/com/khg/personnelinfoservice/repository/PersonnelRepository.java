package tr.com.khg.personnelinfoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.khg.personnelinfoservice.domain.Personnel;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
}
