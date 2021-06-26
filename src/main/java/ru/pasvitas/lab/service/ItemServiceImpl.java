package ru.pasvitas.lab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import ru.pasvitas.lab.model.Catalog;
import ru.pasvitas.lab.model.Item;
import ru.pasvitas.lab.repository.CatalogRepository;
import ru.pasvitas.lab.repository.ItemRepository;

@Singleton
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final CatalogRepository catalogRepository;

    @Inject
    public ItemServiceImpl(ItemRepository itemRepository, CatalogRepository catalogRepository) {
        this.itemRepository = itemRepository;
        this.catalogRepository = catalogRepository;
    }

    @Override
    public List<Item> getItemsByCatalog(long catalogId) {
        Optional<Catalog> catalogOptional = catalogRepository.getCatalogById(catalogId);
        if (catalogOptional.isPresent()) {
            return itemRepository.getItemsForCatalog(catalogOptional.get());
        }
        else {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Item> getItemById(long id) {
        return itemRepository.getItemById(id);
    }

    @Override
    public long createItem(Item item) {
        return itemRepository.saveItem(item).getId();
    }

    @Override
    public long updateItem(Item item) {
        if (itemRepository.getItemById(item.getId()).isPresent()) {
            return itemRepository.saveItem(item).getId();
        }
        else {
            return 0;
        }
    }

    @Override
    public void deleteItem(Item item) {
        if (itemRepository.getItemById(item.getId()).isPresent()) {
            itemRepository.deleteItem(item);
        }
    }
}
