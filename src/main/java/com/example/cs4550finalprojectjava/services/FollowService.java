package com.example.cs4550finalprojectjava.services;

import com.example.cs4550finalprojectjava.models.Follow;
import com.example.cs4550finalprojectjava.models.User;
import com.example.cs4550finalprojectjava.repositories.FollowRepository;
import com.example.cs4550finalprojectjava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.example.cs4550finalprojectjava.services.UserService.USER;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "true")
public class FollowService {

    @Autowired
    FollowRepository followRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/api/follow/{userId}")
    public Follow follow(@PathVariable("userId") String userId, HttpSession session) {
        User followed = userRepository.findById(Integer.parseInt(userId)).get();
        User follower = (User) session.getAttribute(USER);
        List<Follow> followList = followRepository.findFollow(followed.getId(), follower.getId());
        if(followList.size() > 0) {
            return followList.get(0);
        }
        Follow follow = new Follow();
        follow.setFollowed(followed);
        follow.setFollower(follower);
        follow.setFollowedName(followed.getUsername());
        follow.setFollowerName(follower.getUsername());
        followRepository.save(follow);
        return follow;
    }

    @DeleteMapping("/api/unfollow/{userId}")
    public void unfollow(@PathVariable("userId") String userId, HttpSession session) {
        User followed = userRepository.findById(Integer.parseInt(userId)).get();
        User follower = (User) session.getAttribute(USER);
        followRepository.deleteById(followRepository.findFollowId(followed.getId(), follower.getId()));
    }

    @GetMapping("/api/user/{userId}/following")
    public List<User> getFollowing(@PathVariable("userId") String userId) {
        return followRepository.getFollowing(Integer.parseInt(userId));
    }

    @GetMapping("/api/user/{userId}/followers")
    public List<User> getFollowers(@PathVariable("userId") String userId) {
        return followRepository.getFollowers(Integer.parseInt(userId));
    }

    @GetMapping("/api/user/following")
    public List<User> getCurUserFollowing(HttpSession session) {
        User curUser = (User) session.getAttribute(USER);
        return followRepository.getFollowing(curUser.getId());
    }

    @GetMapping("/api/user/followers")
    public List<User> getCurUserFollowers(HttpSession session) {
        User curUser = (User) session.getAttribute(USER);
        return followRepository.getFollowers(curUser.getId());
    }

    @GetMapping("/api/follow")
    public List<Follow> findAllFollows() {
        return (List<Follow>) followRepository.findAll();
    }

    @GetMapping("/api/follow/{id}")
    public Follow findFollowById(@PathVariable("id") String id) {
        return followRepository.findById(Integer.parseInt(id)).get();
    }

    @DeleteMapping("/api/follow/{id}")
    public void deleteFollow(@PathVariable("id") String id) {
        followRepository.deleteById(Integer.parseInt(id));
    }
}

