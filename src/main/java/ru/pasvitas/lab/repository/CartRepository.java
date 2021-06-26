package ru.pasvitas.lab.repository;

import java.util.Optional;
import javax.annotation.ManagedBean;
import javax.ejb.Local;
import ru.pasvitas.lab.model.Cart;
import ru.pasvitas.lab.model.User;

public interface CartRepository {
    Optional<Cart> getCartById(long id);
    Optional<Cart> getCartByUser(User user);
    Cart saveCart(Cart cart);
    void deleteCart(Cart cart);
}
