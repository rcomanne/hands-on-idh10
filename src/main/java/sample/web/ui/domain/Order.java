package sample.web.ui.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "Orders")
@Slf4j
public class Order extends BaseOrder {
    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;

    public Order() {
        this.products = new ArrayList<>();
    }

    public boolean add(Product product) {
        return this.products.add(product);
    }

    @Override
    public int price() {
        int price = 0;
        for (Product product : this.products) {
            price += product.getPrice();
            log.info("Product: {}", product);
        }
        return price;
    }
}
