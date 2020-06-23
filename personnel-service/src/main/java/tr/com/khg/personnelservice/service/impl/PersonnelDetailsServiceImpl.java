package tr.com.khg.personnelservice.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    @HystrixCommand(
        fallbackMethod = "fallBackPersonnelDetails",
        commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
        }
    )
    public PersonnelDetails getPersonnelDetails(String registry) {
        return restTemplate.getForObject("http://PERSONNEL-DETAILS-SERVICE/personnel-details/getPersonnelDetails/" +
                registry, PersonnelDetails.class);
    }

    private PersonnelDetails fallBackPersonnelDetails(String registry) {
        return new PersonnelDetails(0L, "Service Unreachable", "", registry);
    }
}
