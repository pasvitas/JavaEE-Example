package ru.pasvitas.lab.service;

import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import ru.pasvitas.lab.model.Cart;
import ru.pasvitas.lab.model.User;
import ru.pasvitas.lab.repository.UserRepository;
import ru.pasvitas.lab.utils.UuidGenerator;

@Singleton
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UuidGenerator uuidGenerator;

    private final CartService cartService;

    @Inject
    public UserServiceImpl(UserRepository userRepository, UuidGenerator uuidGenerator, CartService cartService) {
        this.userRepository = userRepository;
        this.uuidGenerator = uuidGenerator;
        this.cartService = cartService;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public User getTempUser() {
        User user = new User(null, uuidGenerator.generateUuid(), uuidGenerator.generateUuid(), false, true);
        return userRepository.saveUser(user);
    }

    @Override
    public void transferTempUserToUser(User tempUser, User user) {
        Optional<Cart> cartOptional = cartService.getCart(tempUser);
        cartOptional.ifPresent(cart -> cart.getCartItemList().forEach(item -> {
            cartService.addItemToCart(tempUser, item);
        }));
    }

    @Override
    public long addUser(User user) {
        if (!userRepository.getUserByUsername(user.getUsername()).isPresent()) {
            return userRepository.saveUser(user).getId();
        }
        else {
            return 0;
        }
    }

    @Override
    public long updateUser(User user) {
        if (userRepository.getUserById(user.getId()).isPresent()) {
            return userRepository.saveUser(user).getId();
        }
        else {
            return 0;
        }
    }

    @Override
    public void deleteUser(User user) {
        if (userRepository.getUserById(user.getId()).isPresent()) {
            userRepository.deleteUser(user);
        }
    }
}
