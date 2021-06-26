package ru.pasvitas.lab.repository;

import java.util.List;
import java.util.Optional;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import ru.pasvitas.lab.model.Catalog;

@Stateful
public class CatalogRepositoryImpl implements CatalogRepository {

    @PersistenceContext(unitName = "default", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    public CatalogRepositoryImpl() {
    }

    public CatalogRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Catalog> getCatalogById(long id) {
        return Optional.ofNullable(entityManager.find(Catalog.class, id));
    }

    @Override
    public List<Catalog> getCatalogs() {
        return entityManager.createQuery("SELECT c FROM Catalog c", Catalog.class).getResultList();
    }

    @Override
    public Catalog saveCatalog(Catalog catalog) {
        entityManager.persist(catalog);
        return catalog;
    }

    @Override
    public void deleteCatalog(Catalog catalog) {
        entityManager.remove(catalog);
    }
}
