package tr.com.khg.personnelinfoservice.service.impl;

import org.springframework.stereotype.Service;
import tr.com.khg.personnelinfoservice.domain.Personnel;
import tr.com.khg.personnelinfoservice.repository.PersonnelRepository;
import tr.com.khg.personnelinfoservice.service.PersonnelService;

import java.util.List;

@Service
public class PersonnelServiceImpl implements PersonnelService {

    private final PersonnelRepository personnelRepository;

    public PersonnelServiceImpl(PersonnelRepository personnelRepository) {
        this.personnelRepository = personnelRepository;
    }

    @Override
    public List<Personnel> getAllPersonnel() {
        return personnelRepository.findAll();
    }
}
