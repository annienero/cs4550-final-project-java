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
}
