package com.hyve.app;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hyve.app.config.JokesApiParameters;
import com.hyve.app.convertors.impl.JokeConvertorImpl;
import com.hyve.app.data.ApiJokesResponse;
import com.hyve.app.domain.Joke;
import com.hyve.app.services.JokeService;
import com.hyve.app.services.impl.JokeServiceImpl;
import com.hyve.app.setup.UnitTest;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockserver.model.HttpRequest.request;

public class JokesTests extends UnitTest {

    @Test
    public void itGetsAListOfJokes(){

        //arrange
        setupFactories();
        ObjectNode apiJokeResponse = factory.build(ApiJokesResponse.class).makeJson();
        MockServerClient jokesApi = new ClientAndServer(9090);

        HttpRequest httpRequest = request();
        jokesApi.when(httpRequest).respond(HttpResponse
                .response()
                .withContentType(MediaType.APPLICATION_JSON)
                .withBody(apiJokeResponse.toString()));

        int numberOfJokes = faker.number().numberBetween(0,10);
        JokeService jokeService = setUpJokeService();

        //act
        List<Joke> jokes = jokeService.getJokes(numberOfJokes);

        //assert
        assertNotNull(jokes);
        assertEquals(numberOfJokes,jokes.size());
    }

    private JokeService setUpJokeService(){
        WebClient webClient = WebClient.builder().build();
        JokesApiParameters jokesApiParameters = new JokesApiParameters(UriComponentsBuilder
                .fromUriString("http://localhost:9090").build().toUri(),5);

        return new JokeServiceImpl(webClient,jokesApiParameters,new JokeConvertorImpl(),null);
    }
}
