package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postStore;

    public PostService(PostRepository postStore) {
        this.postStore = postStore;
    }

    public List<Post> getAll() {
        return (List<Post>) postStore.findAll();
    }

    public Optional<Post> findById(int id) {
        return postStore.findById(id);
    }

    public void add(Post post) {
        postStore.save(post);
    }

    public void update(Post post) {
        postStore.save(post);
    }

    public void delete(Post post) {
        postStore.delete(post);
    }
}
