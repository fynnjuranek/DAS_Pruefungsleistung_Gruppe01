package de.leuphana.customer;

import de.leuphana.customer.behaviour.CustomerService;
import de.leuphana.shop.structure.sales.Cart;
import de.leuphana.shop.structure.sales.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class CustomerApplicationTests {
    @Autowired
    CustomerService customerService;

    @Test
    void canCustomerBeAdded() {
        Cart cart = new Cart();
        Customer customer = new Customer(cart);
        Customer addedCustomer = customerService.addCustomerToDatabase(customer);
        Assertions.assertNotNull(addedCustomer);
    }
}
