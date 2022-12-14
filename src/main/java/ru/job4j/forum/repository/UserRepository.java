package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @EntityGraph(attributePaths = {"authorities"})
    Optional<User> findUserByUsername(String username);

}
