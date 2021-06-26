package ru.pasvitas.lab.repository;

import java.util.List;
import java.util.Optional;
import ru.pasvitas.lab.model.Catalog;
import ru.pasvitas.lab.model.Item;

public interface ItemRepository {
    Optional<Item> getItemById(long id);
    List<Item> getItems();
    List<Item> getItemsForCatalog(Catalog catalog);
    Item saveItem(Item item);
    void deleteItem(Item item);
}
