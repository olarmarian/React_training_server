package ro.fortech.comments.service;

import org.springframework.stereotype.Service;
import ro.fortech.comments.model.Comment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CommentsService {

    private List<Comment> commentsList;
    int nextId;

    public CommentsService() {
        commentsList = new ArrayList<>();
        nextId = 1;
    }

    public List<Comment> getAllComments() {
        return commentsList;
    }

    public List<Comment> getCommentsByUser(String user) {
        return commentsList.stream()
                .filter(comment -> comment.getUser().equals(user))
                .collect(Collectors.toList());
    }

    public Comment getCommentById(int id) {
        return commentsList.stream()
                .filter(comment -> comment.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }

    public Comment addComment(Comment comment) {
        comment.setId(nextId);
        nextId++;
        commentsList.add(comment);
        return comment;
    }

    public Comment updateComment(Comment comment, int id) {
        Comment oldComment = getCommentById(id);
        if(oldComment != null){
            int positionOfComment = commentsList.indexOf(oldComment);
            comment.setId(id);
            comment.setLastModifiedDate(LocalDate.now());
            commentsList.set(positionOfComment, comment);
            return comment;
        }
        return null;
    }

    public Comment deleteComment(int id){
        Comment comment = getCommentById(id);
        commentsList.removeIf(commentToDelete -> commentToDelete.getId() == id);
        return comment;
    }
}
