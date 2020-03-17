package com.example.sdnrxmarvelbasic;

import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

public interface CharacterRepo extends ReactiveNeo4jRepository<Character, Long> {

    Mono<Character> getCharacterByName(String name);
}
