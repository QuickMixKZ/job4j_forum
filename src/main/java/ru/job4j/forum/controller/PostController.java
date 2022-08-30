package ru.job4j.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostStoreMem;
import ru.job4j.forum.service.PostService;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/{id}")
    public String post(Model model, @PathVariable int id) {
        Optional<Post> post = postService.findById(id);
        if (post.isEmpty()) {
            throw new IllegalArgumentException("Wrong post's ID");
        }
        model.addAttribute("post", post.get());
        return "post";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("post", new Post());
        return "add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Post post) {
        post.setCreated(LocalDateTime.now());
        postService.add(post);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model,
                       @PathVariable int id) {
        Optional<Post> post = postService.findById(id);
        if (post.isEmpty()) {
            throw new IllegalArgumentException("Wrong post's ID");
        }
        model.addAttribute("post", post.get());
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Post post) {
        postService.update(post);
        return String.format("redirect:/post/%d", post.getId());
    }

}
