package com.example.sdnrxmarvelbasic;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CharacterRepo extends ReactiveNeo4jRepository<Character, Long> {

    @Query("MATCH (c:Character) RETURN c;")
    Flux<Character> findAllCharacters();

    @Query("MATCH (c:Character {name: $name})<-[r:INCLUDES]-(i:ComicIssue) " +
            "RETURN c, collect(r), collect(i);")
    Mono<Character> getCharacterByName(String name);
}
