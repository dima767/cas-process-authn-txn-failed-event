package org.example.dk.cas.events;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CasEventsConfiguration {

    @Bean
    public AuthenticationFailureLoggingCasEventListener authenticationFailureLoggingCasEventListener() {
        return new AuthenticationFailureLoggingCasEventListener();
    }
}
