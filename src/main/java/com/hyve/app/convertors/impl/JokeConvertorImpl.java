package com.hyve.app.convertors.impl;

import com.hyve.app.convertors.JokeConvertor;
import com.hyve.app.data.ApiJokesResponse;
import com.hyve.app.domain.Joke;

public class JokeConvertorImpl implements JokeConvertor {

    @Override
    public Joke convert(ApiJokesResponse apiJokesResponse) {

        Joke joke = new Joke();
        joke.setFact(apiJokesResponse.getValue());
        joke.setJokeId(apiJokesResponse.getId());
        joke.setIconUrl(apiJokesResponse.getIconUrl());
        joke.setUrl(apiJokesResponse.getUrl());

        return joke;
    }
}
