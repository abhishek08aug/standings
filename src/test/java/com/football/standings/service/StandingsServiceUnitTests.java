package com.football.standings.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.football.standings.pojo.Standing;
import com.football.standings.pojo.StandingsResponse;
import com.football.standings.provider.api.StandingsProvider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class StandingsServiceUnitTests {

    @Autowired
    private StandingsService standingsService;

    @MockBean
    private StandingsProvider standingsProvider;

    private List<Standing> countryStandings;
    private List<Standing> leagueStandings;
    private List<Standing> teamStandings;

    @BeforeEach
    public void setup() {
        countryStandings = new ArrayList<>();
        leagueStandings = new ArrayList<>();
        teamStandings = new ArrayList<>();

        Standing standing1 = new Standing();
        standing1.setCountryId(1);
        standing1.setCountryName("England");
        standing1.setLeagueId(1);
        standing1.setLeagueName("a");
        standing1.setTeamId(1);
        standing1.setTeamName("x");
        standing1.setOverallLeaguePosition(1);
        countryStandings.add(standing1);
        leagueStandings.add(standing1);
        teamStandings.add(standing1);

        Standing standing2 = new Standing();
        standing2.setCountryId(1);
        standing2.setCountryName("England");
        standing2.setLeagueId(1);
        standing2.setLeagueName("a");
        standing2.setTeamId(2);
        standing2.setTeamName("y");
        standing2.setOverallLeaguePosition(2);
        countryStandings.add(standing2);
        leagueStandings.add(standing2);

        Standing standing3 = new Standing();
        standing3.setCountryId(1);
        standing3.setCountryName("England");
        standing3.setLeagueId(2);
        standing3.setLeagueName("b");
        standing3.setTeamId(3);
        standing3.setTeamName("z");
        standing3.setOverallLeaguePosition(3);
        countryStandings.add(standing3);
    }

    @Test
    public void testGetStandingsByCountryName() {
        when(standingsProvider.getStandingsByCountryName(anyString())).thenReturn(countryStandings);

        StandingsResponse standingsResponse = standingsService.getStandingsByCountryName("England");
        assertThat(standingsResponse.getStandings().size(), is(3));
    }

    @Test
    public void testGetStandingsByLeagueName() {
        when(standingsProvider.getStandingsByLeagueName(anyString())).thenReturn(leagueStandings);

        StandingsResponse standingsResponse = standingsService.getStandingsByLeagueName("a");
        assertThat(standingsResponse.getStandings().size(), is(2));
    }

    @Test
    public void testGetStandingsByTeamName() {
        when(standingsProvider.getStandingsByTeamName(anyString())).thenReturn(teamStandings);

        StandingsResponse standingsResponse = standingsService.getStandingsByTeamName("x");
        assertThat(standingsResponse.getStandings().size(), is(1));
    }

}
