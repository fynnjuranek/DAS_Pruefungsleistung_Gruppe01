package de.leuphana.customer.structure.database.mapper;

import de.leuphana.customer.entity.CustomerEntity;
import de.leuphana.shop.structure.sales.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity mapToCustomerEntity(Customer customer);
    Customer mapToCustomer(CustomerEntity customerEntity);
}
