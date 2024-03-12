package de.leuphana.customer.behaviour;
import de.leuphana.customer.structure.database.entity.CartEntity;
import de.leuphana.customer.structure.database.entity.CustomerEntity;
import de.leuphana.customer.structure.database.CustomerDatabase;

import de.leuphana.customer.structure.database.mapper.CustomerMapper;
import de.leuphana.shop.structure.sales.CartItem;
import de.leuphana.shop.structure.sales.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerDatabase customerDatabase;

    @Autowired
    CustomerMapper customerMapper;

    public Customer addCustomerToDatabase(Customer customer){
        CustomerEntity foundCustomerEntity = customerDatabase.findCustomerEntityByName(customer.getName());
        if (foundCustomerEntity != null) {
            customerDatabase.deleteById(foundCustomerEntity.getCustomerId());
            // TODO: Right now the articleId is adding up with every article (JPA-Generation), maybe add function to set the articleId after mapping (entity creation).
        }
        CartEntity cartEntity = customerMapper.mapToCartEntity(customer.getCart());

        for (CartItem cartItem : customer.getCart().getCartItems()) {
            cartEntity.addCartItem(cartItem.getArticleId());
        }

        CustomerEntity customerEntity = customerMapper.mapToCustomerEntity(customer);
        customerEntity.setCartEntity(cartEntity);

        // To be 100% sure that the customer got properly saved!
        CustomerEntity savedCustomer = customerDatabase.save(customerEntity);
        return customerMapper.mapToCustomer(savedCustomer);
    }


    public Customer findCustomerByName(String name) {
        CustomerEntity customerEntity = customerDatabase.findCustomerEntityByName(name);
        Customer customer = customerMapper.mapToCustomer(customerEntity);
        return customer;
    }

    public List<Customer> findAllCustomers() {
        List<CustomerEntity> customerEntities = customerDatabase.findAll();
        List<Customer> customers = new ArrayList<>();
        for (CustomerEntity customerEntity : customerEntities){
            customers.add(customerMapper.mapToCustomer(customerEntity));
        }
        return customers;
    }

    public Customer deleteCustomerByName(String name) {
        CustomerEntity customerEntity = customerDatabase.findCustomerEntityByName(name);
        customerDatabase.deleteById(customerEntity.getCustomerId());
        return customerMapper.mapToCustomer(customerEntity);
    }
}
