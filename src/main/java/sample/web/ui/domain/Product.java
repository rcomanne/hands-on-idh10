package sample.web.ui.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private int price;

    public Product(final String name, final int price) {
        this.name = name;
        this.price = price;
    }

    public Product(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
