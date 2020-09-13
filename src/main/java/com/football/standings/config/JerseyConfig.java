package com.football.standings.config;

import com.football.standings.service.StandingsService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("standings")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig()
    {
        register(StandingsService.class);
    }
}
