package tr.com.khg.personnelinfoservice.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tr.com.khg.personnelinfoservice.domain.Personnel;
import tr.com.khg.personnelinfoservice.service.PersonnelService;

import java.util.List;

@RestController
@RequestMapping("/personnel-info")
public class PersonnelResource {

    private final PersonnelService personnelService;

    public PersonnelResource(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Personnel> getAllPersonnel() {
        return personnelService.getAllPersonnel();
    }
}
