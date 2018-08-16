package com.example.cs4550finalprojectjava.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String songId;
    private String title;
    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name="artist_id")
    private User artist;
    @OneToMany()
    @JoinColumn(name="song_id", referencedColumnName="id")
    private List<Review> reviews;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(User artist) {
        this.artist = artist;
    }

    public User getArtist() {
        return artist;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void updateSong(Song song) {
        if (song.title != null) {
            this.title = song.title;
        }
        if (song.songId != null) {
            this.songId = song.songId;
        }
        if (song.artist != null) {
            this.artist = song.artist;
        }
    }
}
