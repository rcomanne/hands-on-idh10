package sample.web.ui.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private Long id;

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
