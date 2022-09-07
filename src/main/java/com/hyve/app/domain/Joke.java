package com.hyve.app.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Joke {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String jokeId;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String iconUrl;

    @Column
    private String url;

    @Column(length = 1000)
    private String fact;

    @PrePersist
    private void onCreate(){
        if(createdAt == null){
            createdAt = LocalDateTime.now();
        }
    }

}
