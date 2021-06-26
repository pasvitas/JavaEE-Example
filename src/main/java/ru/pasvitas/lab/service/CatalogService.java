package ru.pasvitas.lab.service;

import java.util.List;
import java.util.Optional;
import ru.pasvitas.lab.model.Catalog;

public interface CatalogService {
    List<Catalog> getAllCatalogs();
    Optional<Catalog> getCatalog(long id);
    long addCatalog(Catalog catalog);
    long editCatalog(Catalog catalog);
    void deleteCatalog(Catalog catalog);
}
