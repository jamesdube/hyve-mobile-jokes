package com.hyve.app.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.URI;

@AllArgsConstructor
@Getter
public class JokesApiParameters {

    private final URI jokesUri;
    private final int jokesToDownloadCount;
}
