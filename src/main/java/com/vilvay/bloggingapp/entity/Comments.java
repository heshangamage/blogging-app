package com.vilvay.bloggingapp.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "post_id")
    private int postId;
    private String name;
    private String email;
    private String body;
    @Column(name = "created_date")
    private Instant createdOn;
    @Column(name = "modified_date")
    private Instant modifiedOn;

    public Comments(int id, int postId,
                    String name, String email, String body,
                    Instant createdOn, Instant modifiedOn) {
        this.id = id;
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    public Comments() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
