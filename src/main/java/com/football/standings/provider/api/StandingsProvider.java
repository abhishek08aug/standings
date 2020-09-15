package com.football.standings.provider.api;

import com.football.standings.pojo.Standing;

import java.util.List;

public interface StandingsProvider {
    List<Standing> getStandingsByCountryName(String countryName);

    List<Standing> getStandingsByLeagueName(String leagueName);

    List<Standing> getStandingsByTeamName(String teamName);
}
