package com.example.cs4550finalprojectjava.repositories;

import com.example.cs4550finalprojectjava.models.Song;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepository extends CrudRepository<Song, Integer> {
    @Query(value = "SELECT * FROM Song WHERE song_id LIKE %:keyword%", nativeQuery = true)
    List<Song> findAllSongsWithKeyword(@Param("keyword") String keyword);

    @Query("SELECT song FROM Song song WHERE song.songId=:songId")
    Song findSongBySongId(String songId);
}
