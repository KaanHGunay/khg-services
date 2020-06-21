package tr.com.khg.personnelservice.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tr.com.khg.personnelservice.domain.Personnel;
import tr.com.khg.personnelservice.domain.PersonnelDetails;
import tr.com.khg.personnelservice.domain.PersonnelInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/personnel")
public class PersonnelResource {

    private final RestTemplate restTemplate;

    public PersonnelResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/getAllPersonnel", method = RequestMethod.GET)
    public List<Personnel> getAllPersonnel() {
        List<Map<String, Object>> result = restTemplate.getForObject("http://localhost:7001/personnel-info/getAll", List.class);

        List<PersonnelInfo>personnelInfos = new ArrayList<>();

        for (Map<String, Object> r : result) {
            PersonnelInfo personnelInfo = new PersonnelInfo();
            personnelInfo.setName((String) r.get("name"));
            personnelInfo.setSurname((String) r.get("surname"));
            personnelInfo.setRegistry((String) r.get("registry"));
            personnelInfos.add(personnelInfo);
        }

        return personnelInfos.stream().map(personnelInfo -> {
            Personnel personnel = new Personnel();
            personnel.setName(personnelInfo.getName());
            personnel.setSurname(personnelInfo.getSurname());
            personnel.setRegistry(personnelInfo.getRegistry());

            PersonnelDetails personnelDetails = restTemplate.getForObject("http://localhost:7002/personnel-details/getPersonnelDetails/" + personnel.getRegistry(), PersonnelDetails.class);

            personnel.setJob(personnelDetails.getJob());
            personnel.setPlace_of_birth(personnelDetails.getPlace_of_birth());

            return personnel;
        }).collect(Collectors.toList());
    }
}
