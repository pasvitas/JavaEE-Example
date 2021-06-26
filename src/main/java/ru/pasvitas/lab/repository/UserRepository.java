package ru.pasvitas.lab.repository;

import java.util.List;
import java.util.Optional;
import ru.pasvitas.lab.model.User;

public interface UserRepository {
    Optional<User> getUserById(long id);
    Optional<User> getUserByUsername(String username);
    List<User> getUsers();
    User saveUser(User user);
    void deleteUser(User user);
}
