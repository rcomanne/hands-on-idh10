package sample.web.ui.repository;

import org.springframework.data.repository.CrudRepository;
import sample.web.ui.domain.OrderOption;

public interface OrderOptionRepository extends CrudRepository<OrderOption, Long> {
}
