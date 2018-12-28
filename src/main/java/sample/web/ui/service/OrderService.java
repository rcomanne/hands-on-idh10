package sample.web.ui.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.web.ui.domain.BaseOrder;
import sample.web.ui.domain.Order;
import sample.web.ui.domain.Product;
import sample.web.ui.domain.ProductCatalog;
import sample.web.ui.repository.BaseOrderRepository;
import sample.web.ui.repository.ProductCatalogRepository;

import java.util.List;

@Slf4j
@Service
public class OrderService implements IOrderService {
    private BaseOrderRepository baseOrderRepository;
    private ProductCatalogRepository productCatalogRepository;

    @Autowired
    public OrderService(BaseOrderRepository baseOrderRepository, ProductCatalogRepository productCatalogRepository) {
        this.baseOrderRepository = baseOrderRepository;
        this.productCatalogRepository = productCatalogRepository;
    }

    @Override
    public Order createOrder() {
        ProductCatalog productCatalog = productCatalogRepository.findById(1L).orElseThrow(() -> new RuntimeException("ProductCatalog with id 1 not found"));

        Product product = productCatalog.decrementStock(2L);

        Product copy = new Product(product);

        Order order = new Order();
        order = baseOrderRepository.save(order);
        order.add(copy);

        return order;
    }

    @Override
    public List<BaseOrder> findAll() {
        return baseOrderRepository.findAll();
    }
}
