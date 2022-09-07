package com.hyve.app.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("jokes")
public class JokesApiProperties {

    private String url;
    private int numberOfJokes;
}
