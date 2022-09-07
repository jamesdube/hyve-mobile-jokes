package com.hyve.app.data;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ApiJoke {

    private String id;
    private String title;
    private String lang;
    private String text;
    private String clean;
    private String racial;
    private LocalDate date;
}
