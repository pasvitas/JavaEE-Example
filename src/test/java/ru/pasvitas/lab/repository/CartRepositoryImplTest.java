package ru.pasvitas.lab.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import ru.pasvitas.lab.model.Cart;
import ru.pasvitas.lab.model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartRepositoryImplTest {


    private final EntityManager entityManager = TestEntityManagerConfig.getEntityManager();

    private final CartRepositoryImpl cartRepository = new CartRepositoryImpl(entityManager);

    private Cart cart;

    private List<Item> items;

    @BeforeAll
    void setUp() {
        cart = new Cart();
        items = TestEntityManagerConfig.getItems();
    }

    @Order(1)
    @DisplayName("Save cart")
    @Test
    void saveCart() {
        this.cart.setUser(TestEntityManagerConfig.getUsers().get(0));

        List<Item> cartItems = new ArrayList<>();
        cartItems.add(items.get(0));
        cartItems.add(items.get(1));

        this.cart.setCartItemList(cartItems);

        entityManager.getTransaction().begin();
        cartRepository.saveCart(cart);
        entityManager.getTransaction().commit();

        List<Item> expectedCartItems = new ArrayList<>();
        expectedCartItems.add(items.get(0));
        expectedCartItems.add(items.get(1));

        cart = new Cart(1L, TestEntityManagerConfig.getUsers().get(0), expectedCartItems);

        assertEquals(cart, entityManager.find(Cart.class, 1L));
    }

    @Order(2)
    @DisplayName("Get cart by id")
    @Test
    void getCartById() {
        Optional<Cart> cartOptional = cartRepository.getCartById(1);
        assertTrue(cartOptional.isPresent());
        assertEquals(this.cart, cartOptional.get());
    }

    @Order(3)
    @DisplayName("Get cart by user id")
    @Test
    void getCartByUserId() {
        Optional<Cart> cartOptional = cartRepository.getCartByUser(TestEntityManagerConfig.getUsers().get(0));
        assertTrue(cartOptional.isPresent());
        assertEquals(this.cart, cartOptional.get());
    }

    @Order(4)
    @DisplayName("Delete cart")
    @Test
    void deleteCart() {
        Optional<Cart> cartOptional = cartRepository.getCartById(1);
        assertTrue(cartOptional.isPresent());
        assertEquals(this.cart, cartOptional.get());

        entityManager.getTransaction().begin();
        cartRepository.deleteCart(cartOptional.get());
        entityManager.getTransaction().commit();

        Cart foundedCart = entityManager.find(Cart.class, 1L);
        assertNull(foundedCart);
    }
}