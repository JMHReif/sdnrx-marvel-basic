package com.example.sdnrxmarvelbasic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;

@Node
public class Character {
    @Id
    @GeneratedValue
    private Long id;

    private String name, description, thumbnail;

    @Relationship(type = "INCLUDES", direction = INCOMING)
    @JsonIgnoreProperties("characters")
    private List<ComicIssue> characterComics = new ArrayList<>();

    public Character() {
    }

    public Character(String name, String description, String thumbnail) {
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public List<ComicIssue> getCharacterComics() {
        return characterComics;
    }
}
