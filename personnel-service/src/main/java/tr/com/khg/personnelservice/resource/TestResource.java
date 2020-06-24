package tr.com.khg.personnelservice.resource;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tr.com.khg.personnelservice.domain.Personnel;
import tr.com.khg.personnelservice.domain.PersonnelDetails;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestResource {

    private final Environment environment;

    public TestResource(Environment environment) {
        this.environment = environment;
    }

    //  Load balancer'ı test etmek amacıyla eklendi
    @RequestMapping(value = "/getPortName", method = RequestMethod.GET)
    public String getPortNumber() {
        return "Application running on " + environment.getProperty("local.server.port");
    }


}
