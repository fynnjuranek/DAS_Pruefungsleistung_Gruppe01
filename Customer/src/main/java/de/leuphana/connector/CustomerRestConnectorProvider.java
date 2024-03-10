package de.leuphana.connector;

import de.leuphana.customer.behaviour.CustomerService;
import de.leuphana.shop.structure.sales.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CustomerRestConnectorProvider {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/getCustomer/{name}")
    public Customer findCustomerByName(@PathVariable("name") String name) {
        return customerService.findCustomerByName(name);
    }

    @RequestMapping("/getCustomers")
    public List<Customer> findAllCustomers() { return customerService.findAllCustomers(); }

    @PostMapping("/addCustomer")
    public Customer addArticleToDatabase(@RequestBody Customer customer) {
        return customerService.addCustomerToDatabase(customer);
    }

    @RequestMapping("/deleteCustomer/{name}")
    public Customer deleteCustomerByName(@PathVariable("name") String name) {
        return customerService.deleteCustomerByName(name);
    }

}
