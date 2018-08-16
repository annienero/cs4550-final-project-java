package com.example.cs4550finalprojectjava.services;

import com.example.cs4550finalprojectjava.models.Song;
import com.example.cs4550finalprojectjava.models.User;
import com.example.cs4550finalprojectjava.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.example.cs4550finalprojectjava.services.UserService.USER;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "true")
public class SongService {
    @Autowired
    SongRepository songRepository;

    @GetMapping("/api/song")
    public List<Song> findAllSongs() {
        return (List<Song>) songRepository.findAll();
    }

    @GetMapping("/api/song/search/{keyword}")
    public List<Song> findAllSongsWithKeyword(@PathVariable("keyword") String keyword) {
        return songRepository.findAllSongsWithKeyword(keyword);
    }

    @GetMapping("/api/song/{id}")
    public Song findSongById(@PathVariable("id") String id) {
        int songId = Integer.parseInt(id);
        return songRepository.findById(songId).get();
    }

    @GetMapping("/api/song/songId/{songId}")
    public Song findSongBySongId(@PathVariable("songId") String songId) {
        return songRepository.findSongBySongId(songId);
    }

    @PostMapping("/api/song")
    public Song createSong(@RequestBody Song song, HttpSession session) {
        if (songRepository.findSongBySongId(song.getSongId()) == null) {
            song.setArtist((User) session.getAttribute(USER));
            songRepository.save(song);
            return song;
        }
        return null;
    }

    @PutMapping("/api/song/{songId}")
    public Song updateSong(@PathVariable("id") String id, @RequestBody Song song) {
        Song oldSong = songRepository.findById(Integer.parseInt(id)).get();
        oldSong.updateSong(song);
        return songRepository.save(oldSong);
    }

    @DeleteMapping("/api/song/{songId}")
    public void deleteSong(@PathVariable("songId") String songId) {
        songRepository.deleteById(Integer.parseInt(songId));
    }
}
