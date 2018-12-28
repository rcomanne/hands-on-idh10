package sample.web.ui.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;

@Getter
@ToString
@Entity
@Slf4j
public class OrderOption extends DecoratedOrder {
    private String name;
    private int price;

    @Override
    public int price() {
        log.info(this.toString());
        return order.price() + price;
    }

    public OrderOption(final String name, final int price, final BaseOrder order) {
        super(order);
        this.name = name;
        this.price = price;
    }
}
