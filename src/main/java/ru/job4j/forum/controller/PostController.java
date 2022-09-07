package ru.job4j.forum.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.MessageService;
import ru.job4j.forum.service.PostService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class PostController {

    private final PostService postService;
    private final MessageService messageService;

    public PostController(PostService postService, MessageService messageService) {
        this.postService = postService;
        this.messageService = messageService;
    }

    @GetMapping("/post/{id}")
    public String post(Model model, @PathVariable int id) {
        Optional<Post> post = postService.findById(id);
        if (post.isEmpty()) {
            throw new IllegalArgumentException("Wrong post's ID");
        }
        model.addAttribute("post", post.get());
        model.addAttribute("messages", messageService.findByPost(post.get()));
        return "post";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("post", new Post());
        return "add";
    }

    @PostMapping("/add")
    public String add(HttpSession session, @ModelAttribute Post post) {
        post.setAuthor(getCurrentUser());
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

    @PostMapping("/delete")
    public String delete(@ModelAttribute Post post) {
        postService.delete(post);
        return "redirect:/";
    }

    @GetMapping("/addMessage/{postId}")
    public String addMessage(Model model,
                             @PathVariable(name = "postId") int postId) {
        model.addAttribute("postId", postId);
        model.addAttribute("message", new Message());
        return "addMessage";
    }

    @PostMapping("/addMessage/{postId}")
    public String addMessage(@ModelAttribute Message message,
                             @PathVariable(name = "postId") int postId) {
        message.setAuthor(getCurrentUser());
        Optional<Post> post = postService.findById(postId);
        if (post.isEmpty()) {
            throw new IllegalArgumentException("Wrong post's id!");
        }
        message.setPost(post.get());
        messageService.save(message);
        return String.format("redirect:/post/%d", message.getPost().getId());
    }

    @GetMapping("/deleteMessage/{postId}/{messageId}")
    public String deleteMessage(@PathVariable(name = "postId") int postId,
                                @PathVariable(name = "messageId") int messageId) {
        messageService.deleteById(messageId);
        return String.format("redirect:/post/%d", postId);
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
