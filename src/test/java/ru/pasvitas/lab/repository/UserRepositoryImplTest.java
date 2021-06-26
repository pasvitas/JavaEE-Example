package ru.pasvitas.lab.repository;

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
import ru.pasvitas.lab.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryImplTest {

    private final EntityManager entityManager = TestEntityManagerConfig.getEntityManager();

    private final UserRepositoryImpl userRepository = new UserRepositoryImpl(entityManager);

    private List<User> users;

    @BeforeAll
    void setUp() {
        users = TestEntityManagerConfig.getUsers();
    }

    @DisplayName("Save user")
    @Order(1)
    @Test
    void saveUser() {

        User user = new User(null, "Test user 1", "Test password 1", false, false);
        entityManager.getTransaction().begin();
        userRepository.saveUser(user);
        entityManager.getTransaction().commit();
        user.setId(3L);
        users.add(user);

        assertEquals(user, entityManager.find(User.class, 3L));
    }

    @DisplayName("Get user by id")
    @Order(2)
    @Test
    void getUserById() {
        User user = new User(3L, "Test user 1", "Test password 1", false, false);
        Optional<User> userOptional = userRepository.getUserById(3);
        assertTrue(userOptional.isPresent());
        assertEquals(userOptional.get(), user);
    }

    @DisplayName("Get user by username")
    @Order(3)
    @Test
    void getUserByUsername() {
        User user = new User(3L, "Test user 1", "Test password 1", false, false);
        Optional<User> userOptional = userRepository.getUserByUsername("Test user 1");
        assertTrue(userOptional.isPresent());
        assertEquals(userOptional.get(), user);
    }

    @DisplayName("Get all users")
    @Order(4)
    @Test
    void getUsers() {
        assertEquals(users, userRepository.getUsers());
    }

    @DisplayName("Delete user")
    @Order(5)
    @Test
    void deleteUser() {
        entityManager.getTransaction().begin();
        userRepository.deleteUser(entityManager.find(User.class, 3L));
        entityManager.getTransaction().commit();
        assertNull(entityManager.find(User.class, 3L));
    }
}