package tr.com.khg.personneldetailservice.resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tr.com.khg.personneldetailservice.domain.PersonnelDetails;
import tr.com.khg.personneldetailservice.service.PersonnelDetailsService;

import java.util.List;

@RestController
@RequestMapping("/personnel-details")
public class PersonnelDetailsResource {

    private final PersonnelDetailsService personnelDetailsService;

    public PersonnelDetailsResource(PersonnelDetailsService personnelDetailsService) {
        this.personnelDetailsService = personnelDetailsService;
    }

    @RequestMapping(value = "/getPersonnelDetails/{registry}", method = RequestMethod.GET)
    public PersonnelDetails getAllPersonnel(@PathVariable String registry) {
        return personnelDetailsService.getPersonnelDetails(registry);
    }
}
