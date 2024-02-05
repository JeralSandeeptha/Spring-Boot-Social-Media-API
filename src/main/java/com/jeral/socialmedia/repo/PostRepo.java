package com.jeral.socialmedia.repo;

import com.jeral.socialmedia.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

//    @Query("SELECT p FROM Post p WHERE p.user.id = :userId")
//    List<Post> findAllPostsByUserId(@Param("userId") Long userId);

    List<Post> findByUserId(Long userId);
}
