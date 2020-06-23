package tr.com.khg.personnelservice.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tr.com.khg.personnelservice.domain.PersonnelDetails;
import tr.com.khg.personnelservice.service.PersonnelDetailsService;

@Service
public class PersonnelDetailsServiceImpl implements PersonnelDetailsService {

    private final RestTemplate restTemplate;

    public PersonnelDetailsServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallBackPersonnelDetails")
    public PersonnelDetails getPersonnelDetails(String registry) {
        return restTemplate.getForObject("http://PERSONNEL-DETAILS-SERVICE/personnel-details/getPersonnelDetails/" +
                registry, PersonnelDetails.class);
    }

    private PersonnelDetails fallBackPersonnelDetails(String registry) {
        return new PersonnelDetails(0L, "Service Unreachable", "", registry);
    }
}
