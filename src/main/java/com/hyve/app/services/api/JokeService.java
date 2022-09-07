package com.hyve.app.services.api;

import com.hyve.app.domain.Joke;

import java.util.List;

public interface JokeService {

    List<Joke> getJokes(int numberOfJokes);
    Joke getJoke();

    void saveJokes(List<Joke> jokes);
}
