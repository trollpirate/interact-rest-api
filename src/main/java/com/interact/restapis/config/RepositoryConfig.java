package com.interact.restapis.config;

import com.interact.restapis.model.Report;
import com.interact.restapis.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration configuration){
        configuration.exposeIdsFor(Report.class, User.class);
    }

}
