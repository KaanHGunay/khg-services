package tr.com.khg.personneldetailservice.service.impl;

import org.springframework.stereotype.Service;
import tr.com.khg.personneldetailservice.domain.PersonnelDetails;
import tr.com.khg.personneldetailservice.repository.PersonnelDetailsRepository;
import tr.com.khg.personneldetailservice.service.PersonnelDetailsService;

@Service
public class PersonnelDetailsServiceImpl implements PersonnelDetailsService {

    private final PersonnelDetailsRepository personnelDetailsRepository;

    public PersonnelDetailsServiceImpl(PersonnelDetailsRepository personnelDetailsRepository) {
        this.personnelDetailsRepository = personnelDetailsRepository;
    }

    @Override
    public PersonnelDetails getPersonnelDetails(String registry) {
        return personnelDetailsRepository.getByRegistry(registry);
    }
}
