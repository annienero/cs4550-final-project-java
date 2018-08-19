package com.example.cs4550finalprojectjava.repositories;

import com.example.cs4550finalprojectjava.models.Song;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongRepository extends CrudRepository<Song, Integer> {
    @Query("SELECT song FROM Song song WHERE song.songId=:songId")
    Song findSongBySongId(String songId);

    @Query(value = "SELECT * FROM Song WHERE title LIKE %:keyword%", nativeQuery = true)
    List<Song> findAllSongsWithKeywordInTitle(String keyword);

    @Query(value = "SELECT * FROM Song WHERE artist LIKE %:keyword%", nativeQuery = true)
    List<Song> findAllSongsWithKeywordInArtist(String keyword);

    @Query(value = "SELECT avg(rating_value) FROM Song song JOIN Review review ON song.id=review.song_id JOIN Rating rating ON review.id=rating.review_id WHERE rating.rating_type=0 AND song.id=:id GROUP BY song.id", nativeQuery = true)
    double getAvgOverallById(int id);

    @Query(value = "SELECT avg(rating_value) FROM Song song JOIN Review review ON song.id=review.song_id JOIN Rating rating ON review.id=rating.review_id WHERE rating.rating_type=1 AND song.id=:id GROUP BY song.id", nativeQuery = true)
    double getAvgProductionById(int id);

    @Query(value = "SELECT avg(rating_value) FROM Song song JOIN Review review ON song.id=review.song_id JOIN Rating rating ON review.id=rating.review_id WHERE rating.rating_type=2 AND song.id=:id GROUP BY song.id", nativeQuery = true)
    double getAvgVocalsById(int id);

    @Query(value = "SELECT avg(rating_value) FROM Song song JOIN Review review ON song.id=review.song_id JOIN Rating rating ON review.id=rating.review_id WHERE rating.rating_type=3 AND song.id=:id GROUP BY song.id", nativeQuery = true)
    double getAvgInstrumentationById(int id);

    @Query(value = "SELECT avg(rating_value) FROM Song song JOIN Review review ON song.id=review.song_id JOIN Rating rating ON review.id=rating.review_id WHERE rating.rating_type=4 AND song.id=:id GROUP BY song.id", nativeQuery = true)
    double getAvgLyricismById(int id);

    @Query(value = "SELECT avg(rating_value) FROM Song song JOIN Review review ON song.id=review.song_id JOIN Rating rating ON review.id=rating.review_id WHERE rating.rating_type=5 AND song.id=:id GROUP BY song.id", nativeQuery = true)
    double getAvgEmotionById(int id);



}
