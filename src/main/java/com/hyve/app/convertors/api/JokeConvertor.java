package com.hyve.app.convertors.api;

import com.hyve.app.data.ApiJokesResponse;
import com.hyve.app.domain.Joke;

public interface JokeConvertor {

    Joke convert(ApiJokesResponse apiJokesResponse);
}
