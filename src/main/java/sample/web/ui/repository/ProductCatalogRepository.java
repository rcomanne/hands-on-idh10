package sample.web.ui.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sample.web.ui.domain.ProductCatalog;

@Repository
public interface ProductCatalogRepository extends CrudRepository<ProductCatalog, Long> {
}
