package ru.pasvitas.lab.service;

import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import ru.pasvitas.lab.model.Catalog;
import ru.pasvitas.lab.repository.CatalogRepository;

@Singleton
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    @Inject
    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public List<Catalog> getAllCatalogs() {
        return catalogRepository.getCatalogs();
    }

    @Override
    public Optional<Catalog> getCatalog(long id) {
        return catalogRepository.getCatalogById(id);
    }

    @Override
    public long addCatalog(Catalog catalog) {
        return catalogRepository.saveCatalog(catalog).getId();
    }

    @Override
    public long editCatalog(Catalog catalog) {
        if (catalogRepository.getCatalogById(catalog.getId()).isPresent()) {
            return catalogRepository.saveCatalog(catalog).getId();
        }
        else {
            return 0;
        }
    }

    @Override
    public void deleteCatalog(Catalog catalog) {
        if (catalogRepository.getCatalogById(catalog.getId()).isPresent()) {
            catalogRepository.deleteCatalog(catalog);
        }
    }
}
