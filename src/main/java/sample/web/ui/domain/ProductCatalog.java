package sample.web.ui.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ProductCatalog {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;

    public ProductCatalog() {
        this.products = new ArrayList<>();
    }

    public boolean add(Product product) {
        return this.products.add(product);
    }

    public Optional<Product> find (long id) {
        return this.products.stream()
            .filter(product -> product.getId() == id)
            .findFirst();
    }
}
