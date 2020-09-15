package com.football.standings.service;

import com.football.standings.pojo.StandingsResponse;
import com.football.standings.provider.api.StandingsProvider;
import lombok.NonNull;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/")
public class StandingsService implements InitializingBean {

    @Autowired
    private StandingsProvider standingsProvider;

    /* ******************************************************
     * Gives standings for a given country name
     * ******************************************************/
    @GET
    @Path("country-name/{country-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandingsResponse getStandingsByCountryName(@NonNull @PathParam("country-name") String countryName) {
        return new StandingsResponse(standingsProvider.getStandingsByCountryName(countryName));
    }

    /* ******************************************************
     * Gives standings for a given league name
     * ******************************************************/
    @GET
    @Path("league-name/{league-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandingsResponse getStandingsByLeagueName(@NonNull @PathParam("league-name") String leagueName) {
        return new StandingsResponse(standingsProvider.getStandingsByLeagueName(leagueName));
    }

    /* ******************************************************
     * Gives standings for a given team name
     * ******************************************************/
    @GET
    @Path("team-name/{team-name}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandingsResponse getStandingsByTeamName(@NonNull @PathParam("team-name") String teamName) {
        return new StandingsResponse(standingsProvider.getStandingsByTeamName(teamName));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Properties set for StandingsService");
    }
}
