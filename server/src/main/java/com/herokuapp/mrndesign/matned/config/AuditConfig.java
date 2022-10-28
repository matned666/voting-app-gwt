package com.herokuapp.mrndesign.matned.config;

import com.herokuapp.mrndesign.matned.model.security.User;
import com.herokuapp.mrndesign.matned.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"eu.herokuapp.mrndesign.matned"})
@EnableTransactionManagement
@EnableJpaAuditing
public class AuditConfig {

    private final UserService userService;

    public AuditConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public AuditorAware<User> auditorProvider() {
        return new AuditorAwareImpl(userService);
    }

}
