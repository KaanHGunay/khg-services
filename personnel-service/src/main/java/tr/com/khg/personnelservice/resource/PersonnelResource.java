package tr.com.khg.personnelservice.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tr.com.khg.personnelservice.domain.Personnel;
import tr.com.khg.personnelservice.domain.PersonnelDetails;
import tr.com.khg.personnelservice.service.PersonnelDetailsService;
import tr.com.khg.personnelservice.service.PersonnelInfoService;

import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/personnel")
public class PersonnelResource {

    private final PersonnelInfoService personnelInfoService;

    private final PersonnelDetailsService personnelDetailsService;

    public PersonnelResource(PersonnelInfoService personnelInfoService,
                             PersonnelDetailsService personnelDetailsService) {
        this.personnelInfoService = personnelInfoService;
        this.personnelDetailsService = personnelDetailsService;
    }

    @RequestMapping(
        value = "/getAllPersonnel",
        method = RequestMethod.GET,
        consumes = {  // Kabul edilen tipler
            MediaType.APPLICATION_XML, // Xml support
            MediaType.APPLICATION_JSON
        },
        produces = {  // Üretilen tipler
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_JSON
        }
    )
    public List<Personnel> getAllPersonnel() {
        return personnelInfoService.getAllPersonnelInfos().stream().map(personnelInfo -> {
            Personnel personnel = new Personnel();
            personnel.setName(personnelInfo.getName());
            personnel.setSurname(personnelInfo.getSurname());
            personnel.setRegistry(personnelInfo.getRegistry());

            PersonnelDetails personnelDetails = personnelDetailsService.getPersonnelDetails(personnel.getRegistry());

            personnel.setPlace_of_birth(personnelDetails.getPlace_of_birth());
            personnel.setJob(personnelDetails.getJob());
            return personnel;
        }).collect(Collectors.toList());
    }


}
