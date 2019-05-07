package edu.ucmo.postapplication.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private PostRepository postRepository;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public ModelAndView index() {
        List<Post> posts = postRepository.findAllByOrderByCreateDateDesc();

        return new ModelAndView("index", "posts", posts);
    }

    @GetMapping("/{id}")
    public ModelAndView getOne(@PathVariable long id) {
        if (!postRepository.findById(id).isPresent()) {
            return new ModelAndView("not_found");
        }

        Post post = postRepository.findById(id).get();

        return new ModelAndView("posts/detail", "post", post);
    }

    @GetMapping("/new")
    public ModelAndView getNew() {
        return new ModelAndView("posts/new");
    }

    @GetMapping("/{id}/edit")
    public ModelAndView getEdit(@PathVariable long id) {
        if (!postRepository.findById(id).isPresent()) {
            return new ModelAndView("not_found");
        }

        Post post = postRepository.findById(id).get();

        return new ModelAndView("posts/edit", "post", post);
    }

    @GetMapping("/{id}/delete")
    public ModelAndView getDelete(@PathVariable long id) {
        if (!postRepository.findById(id).isPresent()) {
            return new ModelAndView("not_found");
        }

        Post post = postRepository.findById(id).get();

        return new ModelAndView("posts/delete", "post", post);
    }

    @PostMapping
    public ModelAndView create(RedirectAttributes redirect, @RequestParam String title, @RequestParam String body) {
        if (title == null || title.equals("")) {
            redirect.addFlashAttribute("titleError", "Title is required");
        }

        if (body == null || body.equals("")) {
            redirect.addFlashAttribute("bodyError", "Body is required");
        }

        if (!redirect.getFlashAttributes().isEmpty()) {
            redirect.addFlashAttribute("title", title);
            redirect.addFlashAttribute("body", body);
            return new ModelAndView("redirect:/posts/new");
        }

        Post post = Post.builder().title(title).body(body).build();
        postRepository.save(post);

        return new ModelAndView("redirect:/posts");
    }

    @PostMapping("/{id}/edit")
    public ModelAndView edit(Model model, @PathVariable Long id, @RequestParam String title, @RequestParam String body) {
        if (!postRepository.findById(id).isPresent()) {
            return new ModelAndView("not_found");
        }

        Post post = postRepository.findById(id).get();

        if (title == null || title.equals("")) {
            model.addAttribute("titleError", "Title is required");
        }

        if (body == null || body.equals("")) {
            model.addAttribute("bodyError", "Body is required");
        }

        if (model.containsAttribute("titleError") || model.containsAttribute("bodyError")) {
            post.setTitle(title);
            post.setBody(body);
            model.addAttribute("post", post);
            return new ModelAndView("posts/edit");
        }

        post.setTitle(title);
        post.setBody(body);

        postRepository.save(post);

        return new ModelAndView("redirect:/posts/" + post.getId());
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable long id) {
        postRepository.deleteById(id);

        return "redirect:/posts";
    }

}
