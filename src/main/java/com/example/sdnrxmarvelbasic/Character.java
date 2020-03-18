package com.example.sdnrxmarvelbasic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.springframework.data.core.schema.GeneratedValue;
import org.neo4j.springframework.data.core.schema.Id;
import org.neo4j.springframework.data.core.schema.Node;
import org.neo4j.springframework.data.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.springframework.data.core.schema.Relationship.Direction.INCOMING;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<ComicIssue> getCharacterComics() {
        return characterComics;
    }

    public void setCharacterComics(List<ComicIssue> characterComics) {
        this.characterComics = characterComics;
    }
}
