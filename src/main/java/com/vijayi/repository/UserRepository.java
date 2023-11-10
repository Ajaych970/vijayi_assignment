package com.vijayi.repository;

import com.vijayi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    find by using commentTo string
    User findByCommentTo(String commentTo);

//    find by using commentFrom string
    User findByCommentFrom(String commentFrom);

}
