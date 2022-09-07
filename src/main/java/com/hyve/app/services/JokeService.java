package com.hyve.app.services;

import com.hyve.app.domain.Joke;
import reactor.core.publisher.Mono;

import java.util.List;

public interface JokeService {

    List<Joke> getJokes(int numberOfJokes);
    Joke getJoke();

    void saveJokes(List<Joke> jokes);
}
