package de.leuphana.customer.structure.database.mapper;

import de.leuphana.customer.structure.database.entity.CartEntity;
import de.leuphana.customer.structure.database.entity.CartItemEntity;
import de.leuphana.customer.structure.database.entity.CustomerEntity;
import de.leuphana.shop.structure.sales.Cart;
import de.leuphana.shop.structure.sales.CartItem;
import de.leuphana.shop.structure.sales.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity mapToCustomerEntity(Customer customer);
    Customer mapToCustomer(CustomerEntity customerEntity);

    CartEntity mapToCartEntity(Cart cart);

    CartItemEntity mapToCartItemEntity(CartItem cartItem);

}
