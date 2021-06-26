package ru.pasvitas.lab.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import ru.pasvitas.lab.model.Item;
import ru.pasvitas.lab.model.Order;
import ru.pasvitas.lab.model.User;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderRepositoryImplTest {

    private final EntityManager entityManager = TestEntityManagerConfig.getEntityManager();

    private final OrderRepositoryImpl orderRepository = new OrderRepositoryImpl(entityManager);

    private Order order;

    private List<Order> orders;

    private User user;

    private final List<Item> items = TestEntityManagerConfig.getItems();

    private final Date date = new Date();

    @BeforeAll
    void setUp() {
        order = new Order();
        orders = new ArrayList<>();
        user = TestEntityManagerConfig.getUsers().get(0);
    }

    @DisplayName("Save order")
    @org.junit.jupiter.api.Order(1)
    @Test
    void saveOrder() {

        List<Item> orderItemList = new ArrayList<>();
        items.add(items.get(0));
        items.add(items.get(3));

        Order order = new Order(null, user, "Order data", date, orderItemList, false);

        entityManager.getTransaction().begin();
        orderRepository.saveOrder(order);
        entityManager.getTransaction().commit();

        this.order = new Order(1L, user, "Order data", date, orderItemList, false);

        assertEquals(order, entityManager.find(Order.class, 1L));

        orders.add(order);
    }

    @DisplayName("Get order")
    @org.junit.jupiter.api.Order(2)
    @Test
    void getOrder() {
        Optional<Order> orderOptional = orderRepository.getOrder(1);
        assertTrue(orderOptional.isPresent());
        assertEquals(order, orderOptional.get());
    }

    @DisplayName("Get orders")
    @org.junit.jupiter.api.Order(3)
    @Test
    void getOrders() {
        List<Item> orderItemList = new ArrayList<>();
        items.add(items.get(1));
        items.add(items.get(2));
        items.add(items.get(4));

        Order secondOrder = new Order(null, user, "Order data", date, orderItemList, false);

        entityManager.getTransaction().begin();
        entityManager.persist(secondOrder);
        entityManager.getTransaction().commit();
        orders.add(secondOrder);

        assertEquals(orders, orderRepository.getOrders());
    }

    @DisplayName("Get order for user")
    @org.junit.jupiter.api.Order(4)
    @Test
    void getOrdersForUser() {
        Order thirdOrder = new Order(null, TestEntityManagerConfig.getUsers().get(1), "Order data", date, new ArrayList<>(), false);
        entityManager.getTransaction().begin();
        entityManager.persist(thirdOrder);
        entityManager.getTransaction().commit();
        assertEquals(orders, orderRepository.getOrdersForUser(user));
    }

    @DisplayName("Delete order")
    @org.junit.jupiter.api.Order(5)
    @Test
    void deleteOrder() {
        entityManager.getTransaction().begin();
        orderRepository.deleteOrder(entityManager.find(Order.class, 2L));
        entityManager.getTransaction().commit();
        assertNull(entityManager.find(Order.class, 2L));
    }
}