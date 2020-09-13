package com.football.standings.provider.impl;

import com.football.standings.pojo.Country;
import com.football.standings.pojo.League;
import com.football.standings.pojo.Standing;
import com.football.standings.pojo.StandingsResponse;
import com.football.standings.provider.api.DataProvider;
import com.football.standings.provider.api.StandingsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultStandingsProvider implements StandingsProvider {

    @Autowired
    private DataProvider dataProvider;

    @Override
    public StandingsResponse getStandingsByCountryName(String countryName) {
        List<Country> countries = getCountries();
        List<Standing> standings = new ArrayList<>();

        for(Country country : countries) {
            if(countryName.equals(country.getName())) {
                List<League> leaguesForCountry = getLeagues(country.getId());
                for(League leagueForCountry : leaguesForCountry) {
                    List<Standing> standingsForLeague = getStandings(leagueForCountry.getId());
                    for(Standing standingForLeague : standingsForLeague) {
                        standingForLeague.setCountryId(country.getId());
                        standings.add(standingForLeague);
                    }
                }
            }
        }
        return new StandingsResponse(standings);
    }

    @Override
    public StandingsResponse getStandingsByLeagueName(String leagueName) {
        List<Country> countries = getCountries();
        List<Standing> standings = new ArrayList<>();

        for(Country country : countries) {
            List<League> leaguesForCountry = getLeagues(country.getId());
            for(League leagueForCountry : leaguesForCountry) {
                if(leagueName.equals(leagueForCountry.getName())) {
                    List<Standing> standingsForLeague = getStandings(leagueForCountry.getId());
                    for(Standing standingForLeague : standingsForLeague) {
                        standingForLeague.setCountryId(country.getId());
                        standings.add(standingForLeague);
                    }
                }
            }
        }
        return new StandingsResponse(standings);
    }

    @Override
    public StandingsResponse getStandingsByTeamName(String teamName) {
        List<Country> countries = getCountries();
        List<Standing> standings = new ArrayList<>();

        for(Country country : countries) {
            List<League> leaguesForCountry = getLeagues(country.getId());
            for(League leagueForCountry : leaguesForCountry) {
                List<Standing> standingsForLeague = getStandings(leagueForCountry.getId());
                for(Standing standingForLeague : standingsForLeague) {
                    standingForLeague.setCountryId(country.getId());
                    standings.add(standingForLeague);
                }
            }
        }

        standings = standings.stream().filter(standing -> teamName.equals(standing.getTeamName()))
                .collect(Collectors.toList());
        return new StandingsResponse(standings);
    }

    private List<Country> getCountries() {
        return dataProvider.getCountries();
    }

    private List<League> getLeagues(int countryId) {
        return dataProvider.getLeagues(countryId);
    }

    private List<Standing> getStandings(int leagueId) {
        return dataProvider.getStandings(leagueId);
    }
}
