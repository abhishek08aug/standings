package com.football.standings.provider.impl;

import com.football.standings.exception.ServiceException;
import com.football.standings.pojo.Country;
import com.football.standings.pojo.League;
import com.football.standings.pojo.Standing;
import com.football.standings.provider.api.DataProvider;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Component
public class APIV2DataProvider implements DataProvider {

    private static final String API_KEY = "9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978";
    private static final String TARGET_API_V2 = "https://apiv2.apifootball.com";

    private static final String PARAM_API_KEY = "APIkey";
    private static final String PARAM_ACTION = "action";
    private static final String PARAM_COUNTRY_ID = "country_id";
    private static final String PARAM_LEAGUE_ID = "league_id";

    private static final String ACTION_GET_COUNTRIES = "get_countries";
    private static final String ACTION_GET_LEAGUES = "get_leagues";
    private static final String ACTION_GET_STANDINGS = "get_standings";

    private final Client client;

    public APIV2DataProvider() {
        client = ClientBuilder.newClient();
    }

    @Override
    public List<Country> getCountries() {
        Invocation.Builder invocationBuilder = client.target(TARGET_API_V2)
                .queryParam(PARAM_API_KEY, API_KEY)
                .queryParam(PARAM_ACTION, ACTION_GET_COUNTRIES)
                .request(MediaType.APPLICATION_JSON);

        List<Country> countriesList = new ArrayList<>();
        try {
            Country[]  countries = invocationBuilder.get(Country[].class);
            for(Country country : countries) {
                countriesList.add(country);
            }
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch countries from APIV2", e);
        }
        return countriesList;
    }

    @Override
    public List<League> getLeagues(int countryId) {
        Invocation.Builder invocationBuilder = client.target(TARGET_API_V2)
                .queryParam(PARAM_API_KEY, API_KEY)
                .queryParam(PARAM_ACTION, ACTION_GET_LEAGUES)
                .queryParam(PARAM_COUNTRY_ID, countryId)
                .request(MediaType.APPLICATION_JSON);

        List<League> leaguesList = new ArrayList<>();
        try {
            League[]  leagues = invocationBuilder.get(League[].class);
            for(League league : leagues) {
                leaguesList.add(league);
            }
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch leagues from APIV2", e);
        }
        return leaguesList;
    }

    @Override
    public List<Standing> getStandings(int leagueId) {
        Invocation.Builder invocationBuilder = client.target(TARGET_API_V2)
                .queryParam(PARAM_API_KEY, API_KEY)
                .queryParam(PARAM_ACTION, ACTION_GET_STANDINGS)
                .queryParam(PARAM_LEAGUE_ID, leagueId)
                .request(MediaType.APPLICATION_JSON);

        List<Standing> standingsList = new ArrayList<>();
        try {
            Standing[]  standings = invocationBuilder.get(Standing[].class);
            for(Standing standing : standings) {
                standingsList.add(standing);
            }
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch standings from APIV2", e);
        }
        return standingsList;
    }


}
