package sample.web.ui.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.web.ui.domain.Product;
import sample.web.ui.domain.ProductCatalog;
import sample.web.ui.repository.ProductCatalogRepository;

import java.util.List;

@Slf4j
@Service
public class ProductCatalogService implements IProductCatalogService {
    private ProductCatalogRepository productCatalogRepository;

    @Autowired
    public ProductCatalogService(ProductCatalogRepository productCatalogRepository) {
        this.productCatalogRepository = productCatalogRepository;
    }

    @Override
    public ProductCatalog createProductCatalog() {
        ProductCatalog productCatalog = new ProductCatalog();
        productCatalog = productCatalogRepository.save(productCatalog);
        log.debug("{} products in product catalog.", productCatalog.getProducts().size());

        return productCatalog;
    }

    @Override
    public ProductCatalog addProductsToCatalog(List<Product> products, int initialStock, ProductCatalog catalog) {
        for (Product product : products) {
            catalog.add(product, initialStock);
        }
        return catalog;
    }
}
