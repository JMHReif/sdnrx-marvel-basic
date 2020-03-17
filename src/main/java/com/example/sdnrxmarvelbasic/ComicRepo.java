package com.example.sdnrxmarvelbasic;

import org.neo4j.springframework.data.repository.Neo4jRepository;
import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

public interface ComicRepo extends ReactiveNeo4jRepository<ComicIssue, Long> {

    Mono<ComicIssue> getComicIssueByName(String name);
}
