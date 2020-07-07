package tr.com.khg.userservice.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfiguration {

    @Bean
    Logger.Level feignLoggerLever() {
        return Logger.Level.FULL;
    }
}
