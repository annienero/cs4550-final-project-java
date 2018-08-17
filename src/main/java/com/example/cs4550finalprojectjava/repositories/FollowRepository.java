package com.example.cs4550finalprojectjava.repositories;

import com.example.cs4550finalprojectjava.models.Follow;
import com.example.cs4550finalprojectjava.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FollowRepository extends CrudRepository<Follow, Integer> {
    // find followers for followed
    List<User> findDistinctFollowerByFollowed(User followed);
    // find all users followed by follower
    List<User> findDistinctFollowedByFollower(User follower);
}