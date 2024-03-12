package de.leuphana.order.structure.database.mapper;

import de.leuphana.order.structure.database.entity.OrderEntity;
import de.leuphana.order.structure.database.entity.OrderPositionEntity;
import de.leuphana.shop.structure.sales.Order;
import de.leuphana.shop.structure.sales.OrderPosition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity mapToOrderEntity(Order order);
    Order mapToOrder(OrderEntity orderEntity);

    OrderPositionEntity mapToOrderPositionEntity(OrderPosition orderPosition);
    OrderPosition mapToOrderPosition(OrderPositionEntity orderPositionEntity);

}

