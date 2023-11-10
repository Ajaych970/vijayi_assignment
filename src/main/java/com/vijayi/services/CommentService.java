package com.vijayi.services;

import com.vijayi.models.Comment;
import com.vijayi.models.User;
import com.vijayi.repository.CommentRepository;
import com.vijayi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    public String addComment(String commentFrom, String commentTo, String message) {

//        checking validation condition
        if ((commentTo != "" && isValidString(commentTo)) && (commentFrom != "" && isValidString(commentFrom)) && message != "") {

//            fetching user from user repository with the help of comment from
            User userFrom = userRepository.findByCommentFrom(commentFrom);

//            steps to perform operation
//                  step 1: There is two cases : 1 - if user does not exist
//                                               2- if user exist
//                  step 2: Then add the comment in database



//            case 1 : user does not exist
//            step 1
            if (userFrom == null) {
//                if user does not exist, create a new one and set the attribute of user with the help of setters
                User newUserFrom = new User();
                newUserFrom.setCommentFrom(commentFrom);
                newUserFrom.setCommentTo(commentTo);

//                save the user in database
                userRepository.save(newUserFrom);
//                Assign the new user to userFrom
                userFrom = newUserFrom;
            }


//            step 1 : check user exist then don't add user
//            case 2: if user exist then directly add comment in database
            Comment comment = new Comment();
            comment.setMessage(message);
            comment.setCommentDateTime(new Date());

//            fetch id of user and set into PostByUser column
            comment.setPostByUser(userFrom.getUserId());

//            save the comment in database
            commentRepository.save(comment);

            return "Comment added successfully";
        }

//        if above conditions failed then return invalid request
        return "invalid request";
    }



//    checking that string is having any special character or not
    public static boolean isValidString(String str) {
//        pulling out one one character from string and checking that it is special character or not
        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c)) {
//                If a non-letter character is found then the string is invalid
                return false;
            }
        }
//        If NO non-letter characters are found then the string is valid
        return true;
    }


    public List<String> findAllCommentsByUser(Long userId) {
        System.out.println("service " + userId);
//        fetch all comment and store in list
        return commentRepository.findAllCommentsByUser(userId);
    }
}