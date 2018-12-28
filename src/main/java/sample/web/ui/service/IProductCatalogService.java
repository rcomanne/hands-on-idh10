package sample.web.ui.service;

import org.springframework.transaction.annotation.Transactional;
import sample.web.ui.domain.Product;
import sample.web.ui.domain.ProductCatalog;

import java.util.List;

public interface IProductCatalogService {
    @Transactional
    ProductCatalog createProductCatalog();

    @Transactional
    ProductCatalog addProductsToCatalog(List<Product> products, int initialStock, ProductCatalog catalog);
}
