package com.hyve.app.cli;

import com.hyve.app.config.JokesApiParameters;
import com.hyve.app.domain.Joke;
import com.hyve.app.services.JokeService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class Cli implements CommandLineRunner {

    protected JokeService jokeService;
    protected JokesApiParameters jokesApiParameters;

    @Override
    public void run(String... args) throws Exception {

        List<Joke> jokes = jokeService.getJokes(jokesApiParameters.getJokesToDownloadCount());

        jokeService.saveJokes(jokes);
    }
}
