package ImageHoster.controller;


import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Controller
public class CommentController {



    @Autowired
    CommentService commentService;

    @Autowired
    ImageService imageService;


    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(Model model,@RequestParam("comment") String comment, @PathVariable("imageId") int id, @PathVariable("imageTitle") String  imageTitle, HttpSession session) {
        Comment commentnew = new Comment();


        User user = (User) session.getAttribute("loggeduser");
        commentnew.setUser(user);
        commentnew.setText(comment);
        Image image=imageService.getImage(id);
        commentnew.setImage(image);
        commentnew.setCreatedDate(new Date(System.currentTimeMillis()));
        commentService.createPost(commentnew);
        model.addAttribute("tags", image.getTags());
        model.addAttribute("comments", commentService.getAllComments(id));
        model.addAttribute("image", image);
        return "images/image.html";

    }
}
