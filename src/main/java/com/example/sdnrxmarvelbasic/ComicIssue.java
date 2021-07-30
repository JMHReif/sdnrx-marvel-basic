package com.example.sdnrxmarvelbasic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node
public class ComicIssue {
    @Id
    @GeneratedValue
    private Long id;

    private String name, thumbnail;
    private Integer pageCount;
    private Double issueNumber;

    @Relationship(type = "INCLUDES")
    @JsonIgnoreProperties("characterComics")
    public List<Character> characters = new ArrayList<>();

    public ComicIssue() {
    }

    public ComicIssue(String name, String thumbnail, Integer pageCount, Double issueNumber) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.pageCount = pageCount;
        this.issueNumber = issueNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public Double getIssueNumber() {
        return issueNumber;
    }

    public List<Character> getCharacters() {
        return characters;
    }
}
