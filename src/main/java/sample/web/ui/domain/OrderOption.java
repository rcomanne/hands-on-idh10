package sample.web.ui.domain;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
public class OrderOption extends DecoratedOrder {
    private String name;
    private int price;

    @Override
    public int price() {
        return order.price() + price;
    }

    public OrderOption(final String name, final int price, final BaseOrder order) {
        super(order);
        this.name = name;
        this.price = price;
    }
}
