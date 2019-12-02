package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;


    public void createPost(Comment comment)
    {

       commentRepository.createComment(comment);
    }

    public List<Comment> getAllComments(Integer id) {


       return commentRepository.getAllcomments(id);




    }
}
