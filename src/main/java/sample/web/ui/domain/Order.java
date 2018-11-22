package sample.web.ui.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name = "Orders")
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
        }
        return price;
    }
}
