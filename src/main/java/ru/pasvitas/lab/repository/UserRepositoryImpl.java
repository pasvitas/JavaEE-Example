package ru.pasvitas.lab.repository;

import java.util.List;
import java.util.Optional;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import ru.pasvitas.lab.model.User;

@Stateful
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext(unitName = "default", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    public UserRepositoryImpl() {
    }

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class).setParameter("username", username).getSingleResult());
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User saveUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }
}
