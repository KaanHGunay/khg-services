package tr.com.khg.userservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tr.com.khg.userservice.service.UserEntityService;

@EnableGlobalMethodSecurity(prePostEnabled = true)  // Configuration içermesi nedeniyle annotation silinmiştir.
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private Environment environment;
    private UserEntityService userEntityService;

    @Autowired
    @Lazy
    public WebSecurity(Environment environment, UserEntityService userEntityService) {
        this.environment = environment;
        this.userEntityService = userEntityService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFilter authenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(userEntityService, environment, authenticationManager());
        authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
        return authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userEntityService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
            csrf().
                disable().
            authorizeRequests().
                antMatchers(HttpMethod.POST, "/users").
                    hasIpAddress(environment.getProperty("gateway.url")).
            anyRequest().authenticated().
            and().
            addFilter(authenticationFilter()).
            addFilter(new AuthorizationFilter(authenticationManager(), environment));
    }

}
