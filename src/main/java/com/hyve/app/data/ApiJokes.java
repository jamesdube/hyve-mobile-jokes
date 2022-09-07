package com.hyve.app.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiJokes {

    private String description;
    private String language;
    private String background;
    private String category;
    private ApiJoke joke;
}
