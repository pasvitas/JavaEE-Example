package ru.pasvitas.lab.service;

import java.util.Optional;
import ru.pasvitas.lab.model.Cart;
import ru.pasvitas.lab.model.Item;
import ru.pasvitas.lab.model.User;

public interface CartService {
    Optional<Cart> getCart(User user);
    void addItemToCart(User user, Item item);
    void removeItemFromCart(User user, Item item);
}
