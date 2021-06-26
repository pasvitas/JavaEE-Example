package ru.pasvitas.lab.repository;

import java.util.List;
import java.util.Optional;
import ru.pasvitas.lab.model.Catalog;

public interface CatalogRepository {
    Optional<Catalog> getCatalogById(long id);
    List<Catalog> getCatalogs();
    Catalog saveCatalog(Catalog catalog);
    void deleteCatalog(Catalog catalog);
}
