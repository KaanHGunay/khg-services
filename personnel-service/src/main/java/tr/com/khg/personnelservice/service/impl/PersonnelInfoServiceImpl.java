package tr.com.khg.personnelservice.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tr.com.khg.personnelservice.domain.PersonnelInfo;
import tr.com.khg.personnelservice.service.PersonnelInfoService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class PersonnelInfoServiceImpl implements PersonnelInfoService {

    private final RestTemplate restTemplate;

    public PersonnelInfoServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @HystrixCommand(
        fallbackMethod = "fallBackPersonnelInfos",
        commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
        }
    )
    public List<PersonnelInfo> getAllPersonnelInfos() {
        List<Map<String, Object>> result = restTemplate.getForObject(
                "http://PERSONNEL-INFO-SERVICE/personnel-info/getAll", List.class);

        List<PersonnelInfo>personnelInfos = new ArrayList<>();

        for (Map<String, Object> r : result) {
            PersonnelInfo personnelInfo = new PersonnelInfo();
            personnelInfo.setName((String) r.get("name"));
            personnelInfo.setSurname((String) r.get("surname"));
            personnelInfo.setRegistry((String) r.get("registry"));
            personnelInfos.add(personnelInfo);
        }

        return personnelInfos;
    }

    private List<PersonnelInfo> fallBackPersonnelInfos() {
        return Collections.singletonList(new PersonnelInfo(0L, "Server Unreachable", "", "0"));
    }
}
