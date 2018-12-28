package sample.web.ui.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sample.web.ui.domain.Product;
import sample.web.ui.repository.ProductRepository;

@Slf4j
@Service
public class ProductService implements IProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product createProduct(String name, int price) {
        Product product = new Product(name, price);
        repository.save(product);
        log.debug("Created product: {}", product);
        return product;
    }
}
