package ru.pasvitas.lab.service;

import java.util.ArrayList;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import ru.pasvitas.lab.model.Cart;
import ru.pasvitas.lab.model.Item;
import ru.pasvitas.lab.model.User;
import ru.pasvitas.lab.repository.CartRepository;

@Singleton
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Inject
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    @Override
    public Optional<Cart> getCart(User user) {
        return cartRepository.getCartByUser(user);
    }

    @Override
    public void addItemToCart(User user, Item item) {
        Optional<Cart> cartOptional = cartRepository.getCartByUser(user);
        Cart cart = cartOptional.orElseGet(() -> new Cart(null, user, new ArrayList<>()));
        cart.getCartItemList().add(item);
        cartRepository.saveCart(cart);
    }

    public void removeItemFromCart(User user, Item item) {
        Optional<Cart> cartOptional = cartRepository.getCartByUser(user);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.getCartItemList().remove(item);
            cartRepository.saveCart(cart);
        }
    }
}

