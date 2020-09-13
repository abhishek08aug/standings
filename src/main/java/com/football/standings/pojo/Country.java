package com.football.standings.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    @JsonProperty("country_id")
    private int id;

    @JsonProperty("country_name")
    private String name;

    @Override
    public String toString() {
        return "id : " + id + " name : " + name;
    }
}
