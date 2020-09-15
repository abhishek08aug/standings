package com.football.standings.provider.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.football.standings.pojo.Country;
import com.football.standings.pojo.League;
import com.football.standings.pojo.Standing;
import com.football.standings.provider.api.DataProvider;
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
public class DefaultStandingsProviderTests {

    @Autowired
    private StandingsProvider standingsProvider;

    @MockBean
    private DataProvider dataProvider;

    private List<Country> countries;
    private List<League> leagues;
    private List<Standing> standings;

    @BeforeEach
    public void setup() {
        countries = new ArrayList<>();
        leagues = new ArrayList<>();
        standings = new ArrayList<>();

        Country country1 = new Country();
        country1.setId(1);
        country1.setName("England");
        countries.add(country1);

        Country country2 = new Country();
        country2.setId(2);
        country2.setName("France");
        countries.add(country2);

        League league1 = new League();
        league1.setId(1);
        league1.setName("a");
        leagues.add(league1);

        League league2 = new League();
        league2.setId(2);
        league2.setName("b");
        leagues.add(league2);

        Standing standing1 = new Standing();
        standing1.setCountryId(1);
        standing1.setCountryName("England");
        standing1.setLeagueId(1);
        standing1.setLeagueName("a");
        standing1.setTeamId(1);
        standing1.setTeamName("x");
        standing1.setOverallLeaguePosition(1);
        standings.add(standing1);

        Standing standing2 = new Standing();
        standing2.setCountryId(1);
        standing2.setCountryName("England");
        standing2.setLeagueId(1);
        standing2.setLeagueName("a");
        standing2.setTeamId(2);
        standing2.setTeamName("y");
        standing2.setOverallLeaguePosition(2);
        standings.add(standing2);

        Standing standing3 = new Standing();
        standing3.setCountryId(1);
        standing3.setCountryName("England");
        standing3.setLeagueId(2);
        standing3.setLeagueName("b");
        standing3.setTeamId(3);
        standing3.setTeamName("z");
        standing3.setOverallLeaguePosition(3);
        standings.add(standing3);

        when(dataProvider.getCountries()).thenReturn(countries);
        when(dataProvider.getLeagues(anyInt())).thenReturn(leagues);
        when(dataProvider.getStandings(anyInt())).thenReturn(standings);
    }

    @Test
    public void testGetStandingsByCountryName() {
        List<Standing> standings = standingsProvider.getStandingsByCountryName("England");
        assertThat(standings.size(), is(6));
    }

    @Test
    public void testGetStandingsByLeagueName() {
        List<Standing> standings = standingsProvider.getStandingsByLeagueName("a");
        assertThat(standings.size(), is(6));
    }

    @Test
    public void testGetStandingsByTeamName() {
        List<Standing> standings = standingsProvider.getStandingsByTeamName("x");
        assertThat(standings.size(), is(4));
    }

}
