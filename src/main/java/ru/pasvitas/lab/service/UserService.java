package ru.pasvitas.lab.service;

import java.util.Optional;
import ru.pasvitas.lab.model.User;

public interface UserService {
    Optional<User> getUserByUsername(String username);
    User getTempUser();
    void transferTempUserToUser(User tempUser, User user);
    long addUser(User user);
    long updateUser(User user);
    void deleteUser(User user);
}
