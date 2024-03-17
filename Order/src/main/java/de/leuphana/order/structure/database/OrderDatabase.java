package de.leuphana.order.structure.database;

import de.leuphana.order.structure.database.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderDatabase extends MongoRepository<OrderEntity, String> {
}