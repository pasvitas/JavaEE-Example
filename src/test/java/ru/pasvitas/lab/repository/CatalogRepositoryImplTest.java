package ru.pasvitas.lab.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
class CatalogRepositoryImplTest {

    private final EntityManager entityManager = TestEntityManagerConfig.getEntityManager();

    private final CatalogRepositoryImpl catalogRepository = new CatalogRepositoryImpl(entityManager);

    private List<Catalog> catalogs;

    @BeforeAll
    void setUp() {
        catalogs = TestEntityManagerConfig.getCatalogs();
    }

    @DisplayName("Save catalog")
    @Order(1)
    @Test
    void saveCatalog() {

        Catalog catalog = new Catalog();
        catalog.setName("Test catalog");

        entityManager.getTransaction().begin();
        catalogRepository.saveCatalog(catalog);
        entityManager.getTransaction().commit();

        List<Item> expectedItems = new ArrayList<>();

        Catalog expectedCatalog = new Catalog(3L, "Test catalog");

        assertEquals(expectedCatalog, entityManager.find(Catalog.class, 3L));

        catalogs.add(expectedCatalog);

    }

    @DisplayName("Get catalog by id")
    @Order(2)
    @Test
    void getCatalogById() {

        Optional<Catalog> catalogOptional = catalogRepository.getCatalogById(3L);
        assertTrue(catalogOptional.isPresent());
        assertEquals(catalogs.get(2), catalogOptional.get());

    }

    @DisplayName("Get catalogs")
    @Order(3)
    @Test
    void getCatalogs() {

        Catalog catalog = new Catalog();
        catalog.setName("Test catalog 2");

        entityManager.getTransaction().begin();
        entityManager.persist(catalog);
        entityManager.getTransaction().commit();

        catalog.setId(4L);
        catalogs.add(catalog);

        List<Catalog> actualCatalogs = catalogRepository.getCatalogs();
        assertEquals(catalogs, actualCatalogs);
    }

    @DisplayName("Delete catalog")
    @Order(4)
    @Test
    void deleteCatalog() {

        entityManager.getTransaction().begin();
        catalogRepository.deleteCatalog(entityManager.find(Catalog.class, 3L));
        entityManager.getTransaction().commit();

        assertNull(entityManager.find(Catalog.class, 3L));

    }
}