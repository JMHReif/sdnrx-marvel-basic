package com.example.sdnrxmarvelbasic;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterRepo characterRepo;

    public CharacterController(CharacterRepo characterRepo) {
        this.characterRepo = characterRepo;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Character> getCharacters() {
        return characterRepo.findAll();
    }

    @GetMapping("/{name}")
    Mono<Character> getCharacterByName(@PathVariable String name) {
        return characterRepo.getCharacterByName(name);
    }
}
