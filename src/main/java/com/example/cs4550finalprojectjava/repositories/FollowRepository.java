package com.example.cs4550finalprojectjava.repositories;

import com.example.cs4550finalprojectjava.models.Follow;
import com.example.cs4550finalprojectjava.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FollowRepository extends CrudRepository<Follow, Integer> {
    @Query("SELECT follower FROM Follow WHERE followed_id=:followed")
    List<User> getFollowers(int followed);

    @Query("SELECT followed FROM Follow WHERE follower_id=:follower")
    List<User> getFollowing(int follower);

    @Query("SELECT id FROM Follow WHERE follower_id=:follower AND followed_id=:followed")
    int findFollow(int followed, int follower);
}