package com.football.standings.provider.api;

import com.football.standings.pojo.Country;
import com.football.standings.pojo.League;
import com.football.standings.pojo.Standing;

import java.util.List;

public interface DataProvider {
    List<Country> getCountries();

    List<League> getLeagues(int countryId);

    List<Standing> getStandings(int leagueId);
}
