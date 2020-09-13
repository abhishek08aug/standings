package com.football.standings.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StandingsResponse {

    private final List<Standing> standings;

    public StandingsResponse(List<Standing> standings) {
        this.standings = standings;
    }
}
