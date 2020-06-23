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

    // Bulkhead pattern
    // Her service için ayrı bir thread pool oluşturularak diğer service'lerden ayrılması sağlanmaktadır. Bu şekilde
    // diğer service'lerin yavaşlaması bizim elimizde bulunan service'i etkilememektedir.
    @Override
    @HystrixCommand(
        fallbackMethod = "fallBackPersonnelInfos",
        // Ayrılan pool'a verilen isim (Default pool'dan böylelikle ayrılmaktadır.)
        threadPoolKey = "personnelInfoPool",
        threadPoolProperties = {
            // Kabul edilen thread sayısı
            @HystrixProperty(name = "coreSize", value = "20"),
            // Kabul edilemeyip sıraya alınan max thread sayısı
            @HystrixProperty(name = "maxQueueSize", value = "10"),
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
