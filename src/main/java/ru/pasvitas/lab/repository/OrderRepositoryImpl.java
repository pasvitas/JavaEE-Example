package ru.pasvitas.lab.repository;

import java.util.List;
import java.util.Optional;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import ru.pasvitas.lab.model.Order;
import ru.pasvitas.lab.model.User;

@Stateful
public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext(unitName = "default", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    public OrderRepositoryImpl() {
    }

    public OrderRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Order> getOrder(long id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    public List<Order> getOrders() {
        return entityManager.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

    @Override
    public List<Order> getOrdersForUser(User user) {
        return entityManager.createQuery("SELECT o FROM Order o where o.user = :user", Order.class).setParameter("user", user).getResultList();
    }

    @Override
    public Order saveOrder(Order order) {
        entityManager.persist(order);
        return order;
    }

    @Override
    public void deleteOrder(Order order) {
        entityManager.remove(order);
    }
}
