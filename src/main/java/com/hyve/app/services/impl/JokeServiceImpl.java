package com.hyve.app.services.impl;

import com.hyve.app.config.JokesApiParameters;
import com.hyve.app.convertors.JokeConvertor;
import com.hyve.app.data.ApiJokesResponse;
import com.hyve.app.domain.Joke;
import com.hyve.app.repositories.JokeRepository;
import com.hyve.app.services.JokeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
@AllArgsConstructor
public class JokeServiceImpl implements JokeService {

    private final WebClient webClient;
    private final JokesApiParameters jokesApiParameters;
    private final JokeConvertor jokeConvertor;
    private final JokeRepository jokeRepository;

    @Override
    public List<Joke> getJokes(int numberOfJokes) {

        Stream<Integer> integerStream = IntStream.rangeClosed(1,numberOfJokes).boxed();
        return integerStream.parallel()
                .map((i) -> {
                    log(i);
                    return getJoke();
                })
                .collect(Collectors.toList());

    }

    @Override
    public Joke getJoke() {

        ApiJokesResponse apiJokesResponse = webClient.get()
                .uri(jokesApiParameters.getJokesUri())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ApiJokesResponse.class)
                .block();

        return jokeConvertor.convert(apiJokesResponse);
    }

    @Override
    public void saveJokes(List<Joke> jokes) {
        log.info("Saving ({}) joke(s)",jokes.size());
        jokeRepository.saveAll(jokes);
    }

    private void log(int i){
        log.info("Getting joke({})",i);
    }
}
