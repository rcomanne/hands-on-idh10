package sample.web.ui.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.web.ui.crosscutting.MyExecutionTime;
import sample.web.ui.domain.BaseOrder;
import sample.web.ui.domain.Product;
import sample.web.ui.domain.ProductCatalog;
import sample.web.ui.service.IOrderService;
import sample.web.ui.service.IProductCatalogService;
import sample.web.ui.service.IProductService;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
    private final IOrderService orderService;
    private final IProductService productService;
    private final IProductCatalogService productCatalogService;

    @Autowired
    public OrderController(IOrderService orderService, IProductService productService, IProductCatalogService productCatalogService) {
        this.orderService = orderService;
        this.productService = productService;
        this.productCatalogService = productCatalogService;
    }

    @MyExecutionTime
    @Transactional
    @GetMapping(path = "/create")
    public ResponseEntity createAndDecorateOrder() {
        return new ResponseEntity<>(orderService.createOrder(), CREATED);
    }

    @MyExecutionTime
    @GetMapping
    public ResponseEntity<List<BaseOrder>> getOrders() {
        return new ResponseEntity<>(orderService.findAll(), OK);
    }

    @MyExecutionTime
    @Transactional
    @GetMapping(path = "/create-catalog")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductCatalog> createProductCatalog() {
//		create catalog
        ProductCatalog catalog = productCatalogService.createProductCatalog();
//    	create products
        Product schroefje = productService.createProduct("schroefje", 2);
        Product moertje = productService.createProduct("moertje", 1);
//		add products to catalog
        productCatalogService.addProductsToCatalog(Arrays.asList(schroefje, moertje), 5, catalog);
//		return catalog
        return new ResponseEntity<>(catalog, OK);
    }
}
