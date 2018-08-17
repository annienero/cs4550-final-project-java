package com.example.cs4550finalprojectjava.services;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge=3600, allowCredentials = "true")
public class ApiKeyService {
    public static final String MUSIC_KEY = "c758ebc39655796f64621b54221d1477";

    @GetMapping("/api/musickey")
    public String getMusicKey() {
        return MUSIC_KEY;
    }

}
