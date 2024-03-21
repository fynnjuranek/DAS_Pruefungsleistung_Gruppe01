package de.leuphana.connector;

import de.leuphana.shop.structure.sales.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("Customer")
public interface CustomerRestConnectorRequester {

//    @RequestMapping("/getCustomer/{name}")
//    Customer getCustomerByName(@PathVariable("name") String name);

    @RequestMapping("/getCustomer/{customerId}")
    Customer getCustomerByCustomerId(@PathVariable("customerId") Integer customerId);

    @RequestMapping("/getCustomers")
    List<Customer> getAllCustomers();

    @RequestMapping("/addOrderToCustomer/{customerId}/{orderId}")
    Customer addOrderToCustomer(@PathVariable("customerId") Integer customerId, @PathVariable("orderId") String orderId);

    @PostMapping("/addCustomer")
    Customer addCustomer(@RequestBody Customer customer);

    @RequestMapping("/deleteCustomer/{name}")
    boolean deleteCustomer(@PathVariable("name") String name);


}
