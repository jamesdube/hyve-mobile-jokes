package com.hyve.app.config;

import com.hyve.app.convertors.api.JokeConvertor;
import com.hyve.app.convertors.impl.JokeConvertorImpl;
import com.hyve.app.repositories.JokeRepository;
import com.hyve.app.services.api.JokeService;
import com.hyve.app.services.impl.JokeServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
@EnableConfigurationProperties(JokesApiProperties.class)
public class JokesConfig {

    @Bean
    public JokesApiParameters jokesApiParameters(JokesApiProperties jokesApiProperties){
        return new JokesApiParameters(UriComponentsBuilder.fromUriString(jokesApiProperties.getUrl()).build().toUri(),
                jokesApiProperties.getNumberOfJokes());
    }

    @Bean
    public JokeConvertor jokeConvertor(){
        return new JokeConvertorImpl();
    }

    @Bean
    public JokeService jokeService(WebClient.Builder builder, JokesApiParameters jokesApiParameters, JokeConvertor jokeConvertor, JokeRepository jokeRepository){
        return new JokeServiceImpl(builder.build(),jokesApiParameters,jokeConvertor,jokeRepository);
    }
}
