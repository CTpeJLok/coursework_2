package com.example.sport_objects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE CONCAT(LOWER(u.username), ' ', LOWER(u.firstName), ' ', LOWER(u.lastName), ' ', LOWER(u.birthday),  ' ', LOWER(u.phone),  ' ', LOWER(u.email)) LIKE %?1%")
    List<User> searchKeyword(String searchKeyword);

    User findByUsername(String username);
}