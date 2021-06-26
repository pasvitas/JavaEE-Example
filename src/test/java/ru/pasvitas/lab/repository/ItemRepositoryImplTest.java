package ru.pasvitas.lab.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import ru.pasvitas.lab.model.Catalog;
import ru.pasvitas.lab.model.Item;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemRepositoryImplTest {

    private final EntityManager entityManager = TestEntityManagerConfig.getEntityManager();

    private final ItemRepositoryImpl itemRepository = new ItemRepositoryImpl(entityManager);

    private List<Item> items;

    private Catalog catalog = TestEntityManagerConfig.getCatalogs().get(0);

    @BeforeAll
    void setUp() {
        items = TestEntityManagerConfig.getItems();
    }

    @DisplayName("Save item")
    @Order(1)
    @Test
    void saveItem() {

        Item item = new Item(null, catalog, "Test item 1", "Test desc 1", BigDecimal.valueOf(100));

        entityManager.getTransaction().begin();
        itemRepository.saveItem(item);
        entityManager.getTransaction().commit();

        item.setId(6L);
        items.add(item);

        assertEquals(item, entityManager.find(Item.class, 6L));
    }

    @DisplayName("Get item by id")
    @Order(2)
    @Test
    void getItemById() {
        Optional<Item> itemOptional = itemRepository.getItemById(6L);
        assertTrue(itemOptional.isPresent());
        assertEquals(items.get(5), itemOptional.get());
    }

    @DisplayName("Get items")
    @Order(3)
    @Test
    void getItems() {
        assertEquals(items, itemRepository.getItems());
    }

    @DisplayName("Get items for category")
    @Order(4)
    @Test
    void getItemsForCategory() {
        assertEquals(items.stream().filter(item -> item.getCatalog() == catalog).collect(Collectors.toList()), itemRepository.getItemsForCatalog(catalog));
    }

    @DisplayName("Delete item")
    @Order(5)
    @Test
    void deleteItem() {
        entityManager.getTransaction().begin();
        itemRepository.deleteItem(items.get(5));
        entityManager.getTransaction().commit();

        assertNull(entityManager.find(Item.class, 6L));
    }
}