package de.leuphana.connector;

import de.leuphana.customer.behaviour.CustomerService;
import de.leuphana.shop.structure.sales.Cart;
import de.leuphana.shop.structure.sales.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CustomerRestConnectorProvider {

    @Autowired
    CustomerService customerService;

//    @RequestMapping("/getCustomer/{name}")
//    public Customer findCustomerByName(@PathVariable("name") String name) {
//        return customerService.findCustomerByName(name);
//    }

    @RequestMapping("/getCustomer/{customerId}")
    public Customer findCustomerByCustomerId(@PathVariable("customerId") Integer customerId) {
        return customerService.findCustomerByCustomerId(customerId);
    }

    @RequestMapping("/getCustomers")
    public List<Customer> findAllCustomers() { return customerService.findAllCustomers(); }

    @PostMapping("/addCustomer")
    public Customer addCustomerToDatabase(@RequestBody Customer customer) {
        return customerService.addCustomerToDatabase(customer);
    }

    @RequestMapping("/addOrderToCustomer/{customerId}/{orderId}")
    public Customer addOrderToCustomer(@PathVariable("customerId") Integer customerId, @PathVariable("orderId") String orderId) {
        return customerService.addOrderToCustomer(customerId, orderId);
    }

    @RequestMapping("/updateCart")
    public Customer updateCart(@RequestBody Cart cart, @RequestParam Integer customerId) {
        return customerService.updateCart(cart, customerId);
    }


    @RequestMapping("/deleteCustomer/{customerId}")
    public Customer deleteCustomerByCustomerId(@PathVariable("customerId") Integer customerId) {
        return customerService.deleteCustomerByCustomerId(customerId);
    }

}
