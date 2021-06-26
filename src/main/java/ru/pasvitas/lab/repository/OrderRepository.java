package ru.pasvitas.lab.repository;

import java.util.List;
import java.util.Optional;
import ru.pasvitas.lab.model.Order;
import ru.pasvitas.lab.model.User;

public interface OrderRepository {
    Optional<Order> getOrder(long id);
    List<Order> getOrders();
    List<Order> getOrdersForUser(User user);
    Order saveOrder(Order order);
    void deleteOrder(Order order);
}
