package com.vijayi.repository;

import com.vijayi.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

//    manual query to fetch data from database
    @Query("SELECT comment.message FROM Comment comment WHERE comment.postByUser = :userId")
    List<String> findAllCommentsByUser(Long userId);
}
