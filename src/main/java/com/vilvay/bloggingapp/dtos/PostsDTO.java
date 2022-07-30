package com.vilvay.bloggingapp.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostsDTO {
    private int id;
    private String title;
    private String body;
    private int authorId;
    private Instant createdOn;
    private Instant modifiedOn;

    public PostsDTO(int id, String title, String body, int authorId, Instant createdOn, Instant modifiedOn) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.authorId = authorId;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    public PostsDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Instant modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
