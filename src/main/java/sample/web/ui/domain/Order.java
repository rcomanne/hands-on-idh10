package sample.web.ui.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "Orders")
@Getter
public class Order {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;

    public Order() {
        this.products = new ArrayList<>();
    }

    public boolean add(Product product) {
        return this.products.add(product);
    }
}
