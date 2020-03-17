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
public class Comic {
    @Id
    @GeneratedValue
    private Long id;

    private String title, image;
    private Integer pageCount;
    private Double issueNumber;

    @Relationship(type = "APPEARS_IN", direction = INCOMING)
    @JsonIgnoreProperties("characterComics")
    public List<Character> characters = new ArrayList<>();

    public Comic() {
    }

    public Comic(String title, String image, Integer pageCount, Double issueNumber) {
        this.title = title;
        this.image = image;
        this.pageCount = pageCount;
        this.issueNumber = issueNumber;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
