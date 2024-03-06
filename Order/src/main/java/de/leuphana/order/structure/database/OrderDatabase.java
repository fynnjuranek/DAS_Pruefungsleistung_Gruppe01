package de.leuphana.order.structure.database;
import de.leuphana.order.structure.database.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDatabase extends JpaRepository<OrderEntity, Integer> {

}
