package de.leuphana.customer.structure.database;

import de.leuphana.customer.structure.database.entity.CartEntity;
import de.leuphana.customer.structure.database.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDatabase extends JpaRepository<CustomerEntity, Integer> {
    CustomerEntity findCustomerEntityByName(String name);
}