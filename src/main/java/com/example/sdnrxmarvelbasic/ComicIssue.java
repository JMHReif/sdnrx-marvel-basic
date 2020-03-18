package com.example.sdnrxmarvelbasic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.springframework.data.core.schema.GeneratedValue;
import org.neo4j.springframework.data.core.schema.Id;
import org.neo4j.springframework.data.core.schema.Node;
import org.neo4j.springframework.data.core.schema.Relationship;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Double getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(Double issueNumber) {
        this.issueNumber = issueNumber;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}
