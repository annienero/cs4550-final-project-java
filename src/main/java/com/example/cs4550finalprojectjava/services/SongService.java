package com.example.cs4550finalprojectjava.services;

import com.example.cs4550finalprojectjava.models.Role;
import com.example.cs4550finalprojectjava.models.Song;
import com.example.cs4550finalprojectjava.models.User;
import com.example.cs4550finalprojectjava.repositories.SongRepository;
import com.example.cs4550finalprojectjava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static com.example.cs4550finalprojectjava.services.UserService.USER;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "true")
public class SongService {
    @Autowired
    SongRepository songRepository;
    @Autowired
    UserRepository userRepository;
//work
    @GetMapping("/api/song")
    public List<Song> findAllSongs() {
        return (List<Song>) songRepository.findAll();
    }
//work
    @GetMapping("/api/song/search/{keyword}")
    public List<Song> findAllSongsWithKeyword(@PathVariable("keyword") String keyword) {
        return songRepository.findAllSongsWithKeyword(keyword);
    }
//work
    @GetMapping("/api/song/{id}")
    public Song findSongById(@PathVariable("id") String id) {
        int songId = Integer.parseInt(id);
        return songRepository.findById(songId).get();
    }

    @GetMapping("/api/song/songId/{songId}")
    public Song findSongBySongId(@PathVariable("songId") String songId) {
        return songRepository.findSongBySongId(songId);
    }

    @PostMapping("/api/artist/song/")
    public Song createSongByArtist(@RequestBody Song song, HttpSession session) {
        setSongId(song);
        if (songRepository.findSongBySongId(song.getSongId()) == null) {
            song.setArtist((User) session.getAttribute(USER));
            songRepository.save(song);
            return song;
        }
        return null;
    }

    @PostMapping("/api/song")
    public Song createSong(@RequestBody Song song) {
        setSongId(song);
        if (songRepository.findSongBySongId(song.getSongId()) == null) {
            User artist = userRepository.findUserByUsername(song.getArtistName());
            if (artist != null && artist.getRole() == Role.ARTIST) {
                song.setArtist(artist);
            }
            songRepository.save(song);
            return song;
        }
        return null;
    }

    private void setSongId(Song song) {
        song.setSongId(new StringBuilder(song.getTitle()).append("-").append(song.getArtistName())
                .toString().toLowerCase().replaceAll("\\s",""));
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
