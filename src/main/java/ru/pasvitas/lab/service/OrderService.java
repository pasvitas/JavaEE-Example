package ru.pasvitas.lab.service;

import java.util.List;
import java.util.Optional;
import ru.pasvitas.lab.model.Cart;
import ru.pasvitas.lab.model.Item;
import ru.pasvitas.lab.model.Order;
import ru.pasvitas.lab.model.User;

public interface OrderService {
    Optional<Order> getOrderById(long id);
    List<Order> getUserOrders(User user);
    List<Order> getOrders();

    long createOrder(User user, String details, Cart cart);
    long updateOrder(Order order);
    void deleteOrder(Order order);
}
