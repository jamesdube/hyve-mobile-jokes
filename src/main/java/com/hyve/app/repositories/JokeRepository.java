package com.hyve.app.repositories;

import com.hyve.app.domain.Joke;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JokeRepository extends JpaRepository<Joke,Long> {
}
