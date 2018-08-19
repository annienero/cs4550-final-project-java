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
    private String artistName;
    private SongType songType;
    private double avgOverall;
    private double avgProduction;
    private double avgVocals;
    private double avgEmotion;
    private double avgLyricism;
    private double avgInstrumentation;
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

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public SongType getSongType() {
        return songType;
    }

    public void setSongType(SongType songType) {
        this.songType = songType;
    }

    public double getAvgOverall() {
        return avgOverall;
    }

    public void setAvgOverall(double avgOverall) {
        this.avgOverall = avgOverall;
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

    public double getAvgProduction() {
        return avgProduction;
    }

    public void setAvgProduction(double avgProduction) {
        this.avgProduction = avgProduction;
    }

    public double getAvgVocals() {
        return avgVocals;
    }

    public void setAvgVocals(double avgVocals) {
        this.avgVocals = avgVocals;
    }

    public double getAvgEmotion() {
        return avgEmotion;
    }

    public void setAvgEmotion(double avgEmotion) {
        this.avgEmotion = avgEmotion;
    }

    public double getAvgInstrumentation() {
        return avgInstrumentation;
    }

    public void setAvgInstrumentation(double avgInstrumentation) {
        this.avgInstrumentation = avgInstrumentation;
    }

    public double getAvgLyricism() {
        return avgLyricism;
    }

    public void setAvgLyricism(double avgLyricism) {
        this.avgLyricism = avgLyricism;
    }
}
