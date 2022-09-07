package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageStore;

    public MessageService(MessageRepository messageStore) {
        this.messageStore = messageStore;
    }

    public List<Message> findByPost(Post post) {
        return messageStore.findAllByPost(post);
    }

    public void delete(Message message) {
        messageStore.delete(message);
    }

    public void deleteById(int id) {
        messageStore.deleteById(id);
    }

    public void save(Message message) {
        messageStore.save(message);
    }
}
