package sample.web.ui.service;

import org.springframework.transaction.annotation.Transactional;
import sample.web.ui.domain.Product;

public interface IProductService {
    @Transactional
    Product createProduct(String name, int price);
}
