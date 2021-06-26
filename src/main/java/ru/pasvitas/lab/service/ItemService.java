package ru.pasvitas.lab.service;

import java.util.List;
import java.util.Optional;
import ru.pasvitas.lab.model.Item;

public interface ItemService {
    List<Item> getItemsByCatalog(long catalogId);
    Optional<Item> getItemById(long id);
    long createItem(Item item);
    long updateItem(Item item);
    void deleteItem(Item item);
}
