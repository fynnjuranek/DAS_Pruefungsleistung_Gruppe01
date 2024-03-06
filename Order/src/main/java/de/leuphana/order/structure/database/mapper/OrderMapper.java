package de.leuphana.order.structure.database.mapper;
import de.leuphana.order.structure.database.entity.OrderEntity;
import de.leuphana.shop.structure.sales.Order;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface OrderMapper {


    Order mapOrderEntityToOrder(OrderEntity orderEntity);
    OrderEntity mapOrderToOrderEntity(Order order);


}
