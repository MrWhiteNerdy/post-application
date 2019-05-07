package edu.ucmo.postapplication.comments;

import edu.ucmo.postapplication.posts.Post;
import edu.ucmo.postapplication.posts.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @PostMapping
    public ModelAndView newComment(RedirectAttributes redirect, @RequestParam String comment, @RequestParam long postId) {
        if (!postRepository.findById(postId).isPresent()) {
            return new ModelAndView("not_found");
        }

        Post post = postRepository.findById(postId).get();

        if (comment == null || comment.equals("")) {
            redirect.addFlashAttribute("commentError", "Comment is required");
        } else {
            post.addComment(Comment.builder().text(comment).build());

            postRepository.save(post);
        }

        return new ModelAndView("redirect:/posts/" + postId);
    }

    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam long commentId, @RequestParam long postId) {
        if (!postRepository.findById(postId).isPresent()) {
            return new ModelAndView("not_found");
        }

        Post post = postRepository.findById(postId).get();

        if (!commentRepository.findById(commentId).isPresent()) {
            return new ModelAndView("not_found");
        }

        Comment comment = commentRepository.findById(commentId).get();

        post.removeComment(comment);
        postRepository.save(post);

        return new ModelAndView("redirect:/posts/" + postId);
    }

}
