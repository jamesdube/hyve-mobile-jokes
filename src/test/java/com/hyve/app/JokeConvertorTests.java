package com.hyve.app;

import com.hyve.app.convertors.api.JokeConvertor;
import com.hyve.app.convertors.impl.JokeConvertorImpl;
import com.hyve.app.data.ApiJokesResponse;
import com.hyve.app.domain.Joke;
import com.hyve.app.setup.UnitTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JokeConvertorTests extends UnitTest {

    @Test
    public void itConvertsAJokeFromTheApiToAnEntityJoke(){

        setupFactories();
        ApiJokesResponse apiJokesResponse = factory.build(ApiJokesResponse.class).make();
        JokeConvertor jokeConvertor = new JokeConvertorImpl();

        Joke convertedJoke = jokeConvertor.convert(apiJokesResponse);

        //assert
        assertNotNull(convertedJoke);
        assertEquals(apiJokesResponse.getValue(),convertedJoke.getFact());
        assertEquals(apiJokesResponse.getIconUrl(),convertedJoke.getIconUrl());
        assertEquals(apiJokesResponse.getUrl(),convertedJoke.getUrl());
        assertEquals(apiJokesResponse.getId(),convertedJoke.getJokeId());

    }
}
