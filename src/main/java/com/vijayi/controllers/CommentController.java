package com.vijayi.controllers;

import com.vijayi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;


//    This is api to add comment in database
//    postman call : http://localhost:8080/comments/add?commentFrom=USER A &commentTo=USER B&message=COMMENT
//    don't use any space while hitting api, it will give error
//    example : http://localhost:8080/comments/add?commentFrom=mohit&commentTo=akash&message=hello akash@

    @PostMapping("/add")
    public String addComment(@RequestParam("commentFrom") String commentFrom,
                             @RequestParam("commentTo") String commentTo,
                             @RequestParam("message") String message) {
//        System.out.println(commentFrom + " " + commentTo + " " + message);


        return commentService.addComment(commentFrom, commentTo, message);

    }

//    This is API to fetch data from database
//    postman call : http://localhost:8080/comments/get/USER ID
//    don't use any space while hitting api, it will give error
//    example : http://localhost:8080/comments/get/4

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<String>> findAllCommentsByUser(@PathVariable Long userId) {
        System.out.println(userId);
        List<String> comments = commentService.findAllCommentsByUser(userId);
        return ResponseEntity.ok(comments);
    }


}
