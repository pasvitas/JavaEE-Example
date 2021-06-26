package ru.pasvitas.lab.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import ru.pasvitas.lab.model.Catalog;
import ru.pasvitas.lab.model.Item;
import ru.pasvitas.lab.model.User;

public class TestEntityManagerConfig {

    private static TestEntityManagerConfig testEntityManagerConfig;

    private final EntityManager entityManager;

    private static List<Catalog> catalogs;

    private static List<User> users;

    private static List<Item> items;

    private TestEntityManagerConfig() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        this.entityManager = emf.createEntityManager();
        this.entityManager.setFlushMode(FlushModeType.AUTO);
        generateTestData();
    }

    public static EntityManager getEntityManager() {
        if (testEntityManagerConfig == null) {
            testEntityManagerConfig = new TestEntityManagerConfig();
        }
        return testEntityManagerConfig.entityManager;
    }

    public static List<Catalog> getCatalogs() {
        if (catalogs != null) {
            return catalogs;
        }
        else {
            return new ArrayList<>();
        }
    }

    public static List<User> getUsers() {
        if (users != null) {
            return users;
        }
        else {
            return new ArrayList<>();
        }
    }

    public static List<Item> getItems() {
        if (items != null) {
            return items;
        }
        else {
            return new ArrayList<>();
        }
    }

    void generateTestData() {
        EntityTransaction transaction = entityManager.getTransaction();

        Catalog catalog1 = new Catalog(null, "Category 1");
        Catalog catalog2 = new Catalog(null, "Category 2");

        List<Catalog> catalogs = new ArrayList<>();

        catalogs.add(catalog1);
        catalogs.add(catalog2);

        List<Item> itemsCategory1 = new ArrayList<>();
        itemsCategory1.add(new Item(null, catalog1, "Item 1", "Desc 1", BigDecimal.valueOf(15)));
        itemsCategory1.add(new Item(null, catalog1, "Item 2", "Desc 1", BigDecimal.valueOf(25)));
        itemsCategory1.add(new Item(null, catalog1, "Item 3", "Desc 1", BigDecimal.valueOf(35)));

        List<Item> itemsCategory2 = new ArrayList<>();
        itemsCategory2.add(new Item(null, catalog2, "Item 4", "Desc 1",BigDecimal.valueOf(5)));
        itemsCategory2.add(new Item(null, catalog2, "Item 5", "Desc 1", BigDecimal.valueOf(45)));


        transaction.begin();
        catalogs.forEach(entityManager::persist);
        itemsCategory1.forEach(entityManager::persist);
        itemsCategory2.forEach(entityManager::persist);
        transaction.commit();

        List<User> users = new ArrayList<>();
        User user = new User(null, "test", "test", false, false);
        User user2 = new User(null, "test2", "test2", false, false);

        users.add(user);
        users.add(user2);

        transaction.begin();
        users.forEach(entityManager::persist);
        transaction.commit();

        TestEntityManagerConfig.users = users;
        TestEntityManagerConfig.catalogs = catalogs;
        TestEntityManagerConfig.items = Stream.concat(itemsCategory1.stream(), itemsCategory2.stream()).collect(Collectors.toList());
    }
}
