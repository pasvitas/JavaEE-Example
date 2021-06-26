package ru.pasvitas.lab.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import ru.pasvitas.lab.model.Cart;
import ru.pasvitas.lab.model.Item;
import ru.pasvitas.lab.model.Order;
import ru.pasvitas.lab.model.User;
import ru.pasvitas.lab.repository.CartRepository;
import ru.pasvitas.lab.repository.OrderRepository;
import ru.pasvitas.lab.repository.UserRepository;

@Singleton
public class OrderServiceImpl implements OrderService {

    private final CartRepository cartRepository;

    private final OrderRepository orderRepository;

    @Inject
    public OrderServiceImpl(CartRepository cartRepository, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }


    @Override
    public Optional<Order> getOrderById(long id) {
        return orderRepository.getOrder(id);
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderRepository.getOrdersForUser(user);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.getOrders();
    }

    @Override
    public long createOrder(User user, String details, Cart cart) {
        Order order = new Order(null, user, details, new Date(), cart.getCartItemList(), false);
        cartRepository.deleteCart(cart);
        return orderRepository.saveOrder(order).getId();
    }

    @Override
    public long updateOrder(Order order) {
        if (orderRepository.getOrder(order.getId()).isPresent()) {
            return orderRepository.saveOrder(order).getId();
        } else {
            return 0;
        }
    }

    @Override
    public void deleteOrder(Order order) {
        if (orderRepository.getOrder(order.getId()).isPresent()) {
            orderRepository.deleteOrder(order);
        }
    }
}
