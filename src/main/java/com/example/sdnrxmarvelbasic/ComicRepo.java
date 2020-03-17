package com.example.sdnrxmarvelbasic;

import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

public interface ComicRepo extends ReactiveNeo4jRepository<Comic, Long> {

    Mono<Comic> getComicByTitle(String title);
}
