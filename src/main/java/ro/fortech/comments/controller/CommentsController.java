package ro.fortech.comments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fortech.comments.model.Comment;
import ro.fortech.comments.service.CommentsService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @GetMapping("/comments")
    public ResponseEntity getAllComments(){
        List comment = commentsService.getAllComments();

        return new ResponseEntity(comment, HttpStatus.OK);
    }

    @GetMapping("/comments/{user}")
    public ResponseEntity getCommentByUser(@PathVariable("user") String user){
        List comment = commentsService.getCommentsByUser(user);

        return new ResponseEntity(comment, HttpStatus.OK);
    }

    /**
     * Headers:
     * Key: Content-Type
     * Value: application/json
     *
     * Body:
     * {
     *     "content": "string",
     *     "user": "string"
     * }
     */
    @PostMapping("/comments")
    public ResponseEntity addComment(@RequestBody Comment newComment){
        Comment comment = commentsService.addComment(newComment);

        return new ResponseEntity(comment, HttpStatus.CREATED);
    }

    /**
     * Headers:
     * Key: Content-Type
     * Value: application/json
     *
     * Body:
     * {
     *     "content": "string",
     *     "user": "string"
     * }
     */
    @PutMapping("comments/{id}")
    public ResponseEntity updateComment(@RequestBody Comment updateComment, @PathVariable("id") int id){
        try{
            Comment comment =  commentsService.updateComment(updateComment, id);

            return new ResponseEntity(comment, HttpStatus.OK);
        } catch (NoSuchElementException exception){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("comments/{id}")
    public ResponseEntity deleteComment(@PathVariable("id") int id){
        try{
            Comment comment =  commentsService.deleteComment(id);

            return new ResponseEntity(comment, HttpStatus.OK);
        } catch (NoSuchElementException exception){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }




}
