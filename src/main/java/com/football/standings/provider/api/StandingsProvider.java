package com.football.standings.provider.api;

import com.football.standings.pojo.StandingsResponse;

public interface StandingsProvider {
    StandingsResponse getStandingsByCountryName(String countryName);

    StandingsResponse getStandingsByLeagueName(String leagueName);

    StandingsResponse getStandingsByTeamName(String teamName);
}
