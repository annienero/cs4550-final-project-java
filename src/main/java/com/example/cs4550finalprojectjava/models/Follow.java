package com.example.cs4550finalprojectjava.models;

import javax.persistence.*;

@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private User follower;
    @OneToOne
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

}
