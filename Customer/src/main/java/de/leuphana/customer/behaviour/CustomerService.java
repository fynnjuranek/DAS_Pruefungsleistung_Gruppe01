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
//        CustomerEntity foundCustomerEntity = customerDatabase.findCustomerEntityByCustomerId(customer.getCustomerId());
//        if (foundCustomerEntity != null) {
//            customerDatabase.deleteById(foundCustomerEntity.getCustomerId());
//            // TODO: Right now the articleId is adding up with every article (JPA-Generation), maybe add function to set the articleId after mapping (entity creation).
//        }
        CartEntity cartEntity = customerMapper.mapToCartEntity(customer.getCart());
        System.out.println(cartEntity.getCartItems());
        // This is needed to set the relationship between orderId in cart_item_entity
        // and cart_entity (tables). So every cartItem shows the orderId to which it belongs!
        for (CartItemEntity cartItemEntity : cartEntity.getCartItems()) {
            cartItemEntity.setCartEntity(cartEntity);
            System.out.println(cartItemEntity.getArticleId());
        }
        CustomerEntity customerEntity = customerMapper.mapToCustomerEntity(customer);
        customerEntity.setCartEntity(cartEntity);
        // To be 100% sure that the customer got properly saved!
        CustomerEntity savedCustomer = customerDatabase.save(customerEntity);
        // This needs to be explicitly set because otherwise the cart is null in "mappedCustomer"
        Customer mappedCustomer = customerMapper.mapToCustomer(savedCustomer);
        mappedCustomer.setCart(customerMapper.mapToCart(cartEntity));
        System.out.println(mappedCustomer.getCart().getCartItems());
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
//        customer.addOrder(orderId);
        System.out.println(customer.getOrderIDs());
        customer.setCustomerId(customerEntity.getCustomerId());
        customer.setCart(customerMapper.mapToCart(customerEntity.getCartEntity()));
        customer.getCart().setId(cartEntity.getId());
//        customer.getCart().

//        customer.getCart().setCartItems(null);
//        customer.setCart(null);

        CustomerEntity mappedCustomer = customerMapper.mapToCustomerEntity(customer);
        mappedCustomer.setCartEntity(customerMapper.mapToCartEntity(customer.getCart()));
        mappedCustomer.addOrder(orderId);
        System.out.println(mappedCustomer.getOrderIDs());
//        mappedCustomer.getCartEntity().setCartItems(null);
//        mappedCustomer.setCartEntity(null);


        return customerMapper.mapToCustomer(customerDatabase.save(mappedCustomer));
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

    public Customer deleteCustomerByName(String name) {
        CustomerEntity customerEntity = customerDatabase.findCustomerEntityByName(name);
        customerDatabase.deleteById(customerEntity.getCustomerId());
        return customerMapper.mapToCustomer(customerEntity);
    }
}
