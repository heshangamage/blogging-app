package com.vilvay.bloggingapp.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "posts")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String body;
    private int authorId;
    @Column(name = "created_date")
    private Instant createdOn;
    @Column(name = "modified_date")
    private Instant modifiedOn;

    public Posts(int id, String title, String body, int authorId, Instant createdOn, Instant modifiedOn) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.authorId = authorId;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    public Posts() {
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
