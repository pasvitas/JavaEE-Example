package ru.pasvitas.lab.service;

import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import ru.pasvitas.lab.model.User;
import ru.pasvitas.lab.repository.UserRepository;

@Singleton
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    @Inject
    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User authorize(String username, String password) {
        Optional<User> userOptional = userRepository.getUserByUsername(username);
        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            return userOptional.get();
        }
        else {
            return null;
        }
    }
}
