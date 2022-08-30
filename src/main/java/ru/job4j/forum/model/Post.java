package ru.job4j.forum.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Objects;

public class Post {
    private int id;
    private String name;
    private String description;
    private LocalDateTime created;

    public static Post of(String name, String description, int id) {
        Post post = new Post();
        post.setCreated(LocalDateTime.now());
        post.name = name;
        post.description = description;
        post.id = id;
        return post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id
                && Objects.equals(name, post.name)
                && Objects.equals(description, post.description)
                && Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, created);
    }
}
