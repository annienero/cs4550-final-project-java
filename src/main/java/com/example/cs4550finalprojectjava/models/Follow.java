package com.example.cs4550finalprojectjava.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String followerName;
    private String followedName;
    @ManyToOne
    @JsonIgnore
    private User follower;
    @ManyToOne
    @JsonIgnore
    private User followed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowed() {
        return followed;
    }

    public void setFollowed(User followed) {
        this.followed = followed;
    }

    public String getFollowerName() {
        return followerName;
    }

    public void setFollowerName(String followerName) {
        this.followerName = followerName;
    }

    public String getFollowedName() {
        return followedName;
    }

    public void setFollowedName(String followedName) {
        this.followedName = followedName;
    }
}
