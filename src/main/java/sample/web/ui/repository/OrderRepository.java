package sample.web.ui.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sample.web.ui.domain.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();
}
