package com.football.standings;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.football.standings.pojo.StandingsResponse;
import com.football.standings.service.StandingsService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class StandingsApplicationTests {

	@Autowired
	private StandingsService standingsService;

	@Test
	public void testGetStandingsByCountryName() {
		StandingsResponse standingsResponse = standingsService.getStandingsByCountryName("England");
		assertThat(standingsResponse.getStandings().size(), is(not(equalTo(0))) );
	}

	@Test
	public void testGetStandingsByLeagueName() {
		StandingsResponse standingsResponse = standingsService.getStandingsByLeagueName("Championship");
		assertThat(standingsResponse.getStandings().size(), is(not(equalTo(0))) );
	}

	@Test
	public void testGetStandingsByTeamName() {
		StandingsResponse standingsResponse = standingsService.getStandingsByTeamName("Reading");
		assertThat(standingsResponse.getStandings().size(), is(not(equalTo(0))) );
	}
}
