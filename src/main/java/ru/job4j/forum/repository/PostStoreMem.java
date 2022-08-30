package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostStoreMem {

    private final Map<Integer, Post> posts = new HashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public PostStoreMem() {
        posts.put(id.incrementAndGet(), Post.of("Флудилка", "Общение на свободные темы", id.get()));
        posts.put(id.incrementAndGet(), Post.of("Продам гараж", "Большой, просторный, светлый, уютный", id.get()));
        posts.put(id.incrementAndGet(), Post.of("Продам машину Москвич", "На ходу, почти новая", id.get()));
    }

    public List<Post> getAll() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> findById(int id) {
        return Optional.ofNullable(posts.get(id));
    }

    public void add(Post post) {
        post.setId(id.incrementAndGet());
        posts.put(post.getId(), post);
    }

    public void update(Post post) {
        posts.put(post.getId(), post);
    }
}
