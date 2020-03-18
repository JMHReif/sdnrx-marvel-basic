package com.example.sdnrxmarvelbasic;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/comics")
public class ComicController {
    private final ComicRepo comicRepo;

    public ComicController(ComicRepo comicRepo) {
        this.comicRepo = comicRepo;
    }

    //@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @GetMapping
    Flux<ComicIssue> getComics() {
        return comicRepo.findAll();
    }

    @GetMapping("/{name}")
    Mono<ComicIssue> getComicIssueByName(@PathVariable String name) {
        return comicRepo.getComicIssueByName(name);
    }
}
