package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostStoreMem;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostStoreMem postStore;

    public PostService(PostStoreMem postStore) {
        this.postStore = postStore;
    }

    public List<Post> getAll() {
        return postStore.getAll();
    }

    public Optional<Post> findById(int id) {
        return postStore.findById(id);
    }

    public void add(Post post) {
        postStore.add(post);
    }

    public void update(Post post) {
        postStore.update(post);
    }
}
