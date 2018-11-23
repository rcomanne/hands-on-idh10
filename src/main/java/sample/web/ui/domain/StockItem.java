package sample.web.ui.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class StockItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Product product;
    private int quantity;

    StockItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public StockItem decrementStock() {
        quantity --;
        return this;
    }
}
