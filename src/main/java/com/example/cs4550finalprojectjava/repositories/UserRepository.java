package com.example.cs4550finalprojectjava.repositories;

import com.example.cs4550finalprojectjava.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT user FROM User user WHERE user.username=:username")
    User findUserByUsername(String username);
    @Query("SELECT user FROM User user WHERE user.username=:username AND user.password=:password")
    User findUserByUsernameAndPassword(String username, String password);
    @Query(value = "SELECT * FROM User WHERE username LIKE %:keyword%", nativeQuery = true)
    List<User> findAllUsersWithKeywordInUsername(String keyword);
}
