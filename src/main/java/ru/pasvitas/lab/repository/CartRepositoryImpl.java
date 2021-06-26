package ru.pasvitas.lab.repository;

import java.util.Optional;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import ru.pasvitas.lab.model.Cart;
import ru.pasvitas.lab.model.User;

@Stateful
public class CartRepositoryImpl implements CartRepository {

    @PersistenceContext(unitName = "default", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    public CartRepositoryImpl() {
    }

    public CartRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Cart> getCartById(long id) {
        return Optional.ofNullable(entityManager.find(Cart.class, id));
    }

    @Override
    public Optional<Cart> getCartByUser(User user) {
        Cart result;
        try {
            Query query = entityManager.createQuery("FROM Cart c WHERE c.user = :user", Cart.class).setParameter("user", user);
            result = (Cart) query.getSingleResult();
        }
        catch (NoResultException e) {
            result = null;
        }
        return Optional.ofNullable(result);
    }

    @Override
    public Cart saveCart(Cart cart) {
        entityManager.persist(cart);
        return cart;
    }

    @Override
    public void deleteCart(Cart cart) {
        entityManager.remove(cart);
    }
}
