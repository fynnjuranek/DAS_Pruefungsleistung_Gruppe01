package de.leuphana.customer.behaviour;
import de.leuphana.customer.structure.database.entity.CartEntity;
import de.leuphana.customer.structure.database.entity.CartItemEntity;
import de.leuphana.customer.structure.database.entity.CustomerEntity;
import de.leuphana.customer.structure.database.CustomerDatabase;

import de.leuphana.customer.structure.database.mapper.CustomerMapper;
import de.leuphana.shop.structure.sales.Cart;
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

        CustomerEntity customerEntity = customerMapper.mapToCustomerEntity(customer);
        CartEntity cartEntity = customerMapper.mapToCartEntity(customer.getCart());
        customerEntity.setCartEntity(cartEntity);
        // To be 100% sure that the customer got properly saved!
        CustomerEntity savedCustomer = customerDatabase.save(customerEntity);
        // This needs to be explicitly set because otherwise the cart is null in "mappedCustomer"
        Customer mappedCustomer = customerMapper.mapToCustomer(savedCustomer);
//        mappedCustomer.getCart().setId(cartEntity.getId());
//        mappedCustomer.setCart(customerMapper.mapToCart(cartEntity));
//        System.out.println(mappedCustomer.getCart().getCartItems());
        return mappedCustomer;
    }

    public Cart addArticleToCart(Integer customerId, Integer articleId, Integer articleQuantity){
        CustomerEntity customerEntity = customerDatabase.findCustomerEntityByCustomerId(customerId);
        Cart cart = customerMapper.mapToCart(customerEntity.getCartEntity());
        // TODO: implement this
//        cart.addCartItem();
        return null;
    }

    public Customer addOrderToCustomer(Integer customerId, String orderId) {
        CustomerEntity customerEntity = customerDatabase.findCustomerEntityByCustomerId(customerId);
        CartEntity cartEntity = customerEntity.getCartEntity();
        Customer customer = customerMapper.mapToCustomer(customerEntity);
        customer.addOrder(orderId);

        CustomerEntity mappedCustomer = customerMapper.mapToCustomerEntity(customer);
        mappedCustomer.setCartEntity(cartEntity);
        System.out.println(mappedCustomer.getOrderIDs());
        System.out.println(cartEntity.getId() + " this should be the id of cartEntity");

        return customerMapper.mapToCustomer(customerDatabase.save(mappedCustomer));
    }

    public Customer updateCart(Cart cart, Integer customerId) {
        CustomerEntity customerEntity = customerDatabase.findCustomerEntityByCustomerId(customerId);
        // Set the id of normal cart before mapping to entity because otherwise the id will be new generated, and it needs to stay the same!
        cart.setId(customerEntity.getCartEntity().getId());
        CartEntity cartEntity = customerMapper.mapToCartEntity(cart);
        System.out.println(cartEntity.getId() + " this should be the id of cartEntity");
        for (CartItemEntity cartItemEntity : cartEntity.getCartItems()) {
            cartItemEntity.setCartEntity(cartEntity);
            System.out.println("cartItemEntity id: " + cartItemEntity.getCartEntity().getId());
        }
        customerEntity.setCartEntity(cartEntity);
        CustomerEntity savedCustomer = customerDatabase.save(customerEntity);
        Customer mappedCustomer = customerMapper.mapToCustomer(savedCustomer);
        mappedCustomer.setCart(customerMapper.mapToCart(cartEntity));

        return mappedCustomer;
    }

    public Customer findCustomerByCustomerId(Integer customerId) {
        CustomerEntity customerEntity = customerDatabase.findCustomerEntityByCustomerId(customerId);
        Customer customer = customerMapper.mapToCustomer(customerEntity);
        // customer orders needs to be set separately because it's null otherwise
        if (customerEntity.getOrderIDs() != null) {
            customer.setOrders(customerEntity.getOrderIDs());
        }
        customer.setCart(customerMapper.mapToCart(customerEntity.getCartEntity()));

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

    public Customer deleteCustomerByCustomerId(Integer customerId) {
        CustomerEntity customerEntity = customerDatabase.findCustomerEntityByCustomerId(customerId);
        customerDatabase.deleteById(customerEntity.getCustomerId());
        return customerMapper.mapToCustomer(customerEntity);
    }
}
