package com.example.cs4550finalprojectjava.services;


import com.example.cs4550finalprojectjava.models.User;
import com.example.cs4550finalprojectjava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge=3600, allowCredentials = "true")
public class UserService {

    public static final String USER = "user";

    @Autowired
    UserRepository userRepository;
//work
    @PostMapping("/api/user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
//work
    @GetMapping("/api/user")
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }
//work
    @GetMapping("/api/user/{id}")
    public User findUserById(@PathVariable("id") String id) {
        int userId = Integer.parseInt(id);
        return userRepository.findById(userId).get();
    }
// role no work :////
    @PutMapping("/api/user/{id}")
    public User updateUser(@PathVariable("id") String id, @RequestBody User user) {
        int userId = Integer.parseInt(id);
        User newUser = userRepository.findById(userId).get();
        newUser.updateUser(user);
        return userRepository.save(newUser);
    }

    @GetMapping("/api/user/username/{username}")
    public User findUserByUsername(@PathVariable("username") String username) {
        return userRepository.findUserByUsername(username);
    }

    @PostMapping("/api/register")
    public User register(@RequestBody User user, HttpSession session) {
        if (userRepository.findUserByUsername(user.getUsername()) == null) {
            userRepository.save(user);
            session.setAttribute(USER, user);
            return user;
        }
        return null;
    }

    @PostMapping("/api/login")
    public User login(@RequestBody User user, HttpSession session) {
        User myUser = userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (myUser == null) {
            return null;
        }
        session.setAttribute(USER, myUser);
        return myUser;
    }

    @GetMapping("/api/profile")
    public User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute(USER);
    }

    @PostMapping("/api/logout")
    public User logout(HttpSession session) {
        User currentUser = (User) session.getAttribute(USER);
        session.invalidate();
        return currentUser;
    }

    @DeleteMapping("/api/user/{userId}")
    public void deleteUser(@PathVariable("userId") String userId) {
        userRepository.deleteById(Integer.parseInt(userId));
    }

}
