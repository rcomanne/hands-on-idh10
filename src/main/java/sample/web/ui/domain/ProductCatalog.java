package sample.web.ui.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Entity
public class ProductCatalog {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private Map<Long, StockItem> products;

    public ProductCatalog() {
        this.products = new HashMap<>();
    }

    public void add(Product product, int quantity) {
        assert (product.getId() != null);

        products.put(product.getId(), new StockItem(product, quantity));
    }

    public Product decrementStock(Long productId) {
        assert (products.containsKey(productId));
        assert (products.get(productId).getQuantity() >= 0);

        StockItem item = products.get(productId);
        products.put(productId, item.decrementStock());
        return item.getProduct();
    }
}
