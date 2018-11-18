package sample.web.ui.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import sample.web.ui.domain.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();
}
