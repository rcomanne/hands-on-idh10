package sample.web.ui.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public abstract class DecoratedOrder extends BaseOrder {
    @OneToOne
    BaseOrder order;

    DecoratedOrder(final BaseOrder order){
        this.order = order;
    }
}
