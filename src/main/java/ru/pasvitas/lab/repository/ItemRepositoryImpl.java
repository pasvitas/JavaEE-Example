package ru.pasvitas.lab.repository;

import java.util.List;
import java.util.Optional;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import ru.pasvitas.lab.model.Catalog;
import ru.pasvitas.lab.model.Item;

@Stateful
public class ItemRepositoryImpl implements ItemRepository {

    @PersistenceContext(unitName = "default", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    public ItemRepositoryImpl() {
    }

    public ItemRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Item> getItemById(long id) {
        return Optional.ofNullable(entityManager.find(Item.class, id));
    }

    @Override
    public List<Item> getItems() {
        return entityManager.createQuery("FROM Item i", Item.class).getResultList();
    }

    @Override
    public List<Item> getItemsForCatalog(Catalog catalog) {
        return entityManager.createQuery("FROM Item i WHERE i.catalog = :catalog", Item.class).setParameter("catalog", catalog).getResultList();
    }

    @Override
    public Item saveItem(Item item) {
        entityManager.persist(item);
        return item;
    }

    @Override
    public void deleteItem(Item item) {
        entityManager.remove(item);
    }
}
