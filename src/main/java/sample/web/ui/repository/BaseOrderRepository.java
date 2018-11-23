package sample.web.ui.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sample.web.ui.domain.BaseOrder;

import java.util.List;

@Repository
public interface BaseOrderRepository extends CrudRepository<BaseOrder, Long> {
    List<BaseOrder> findAll();
}
