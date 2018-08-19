package com.example.cs4550finalprojectjava.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String bannerImage;
    private String profilePic;
    @OneToMany(mappedBy="user")
    private List<Review> reviews;
    @OneToMany()
    @JoinColumn(name="artist_id", referencedColumnName="id")
    private List<Song> uploads;
    @OneToMany(mappedBy="user")
    private List<Comment> comments;
    @OneToMany(mappedBy="follower")
    private List<Follow> followedList;
    @OneToMany(mappedBy="followed")
    private List<Follow> followerList;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Song> getUploads() {
        return uploads;
    }

    public void setUploads(List<Song> uploads) {
        this.uploads = uploads;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void updateUser(User user) {
        this.role = user.role;
        if (user.username != null) {
            this.username = user.username;
        }
        if (user.password != null) {
            this.password = user.password;
        }
        if (user.firstName != null) {
            this.firstName = user.firstName;
        }
        if (user.lastName != null) {
            this.lastName = user.lastName;
        }
        if (user.email != null) {
            this.email = user.email;
        }
        if (user.bannerImage != null) {
            this.bannerImage = user.bannerImage;
        }
        if (user.profilePic != null) {
            this.profilePic = user.profilePic;
        }
    }

    public List<Follow> getFollowedList() {
        return followedList;
    }

    public void setFollowedList(List<Follow> followedList) {
        this.followedList = followedList;
    }

    public List<Follow> getFollowerList() {
        return followerList;
    }

    public void setFollowerList(List<Follow> followerList) {
        this.followerList = followerList;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}