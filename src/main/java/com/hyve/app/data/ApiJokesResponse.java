package com.hyve.app.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiJokesResponse {

    private List<String> categories;
    @JsonProperty("icon_url")
    private String iconUrl;
    private String id;
    private String url;
    private String value;

}
