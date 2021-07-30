package com.example.sdnrxmarvelbasic;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ComicRepo extends ReactiveNeo4jRepository<ComicIssue, Long> {

    @Query("MATCH (i:ComicIssue)-[r:INCLUDES]->(c:Character) RETURN i, collect(r), collect(c);")
    Flux<ComicIssue> findAllComics();

    @Query("MATCH (i:ComicIssue {name: $name})-[r:INCLUDES]->(c:Character) RETURN i, collect(r), collect(c);")
    Mono<ComicIssue> getComicIssueByName(String name);
}
