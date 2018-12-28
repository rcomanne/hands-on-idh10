package sample.web.ui.service;

import org.springframework.transaction.annotation.Transactional;
import sample.web.ui.domain.BaseOrder;
import sample.web.ui.domain.Order;

import java.util.List;

public interface IOrderService {
    @Transactional
    Order createOrder();

    @Transactional
    List<BaseOrder> findAll();
}
