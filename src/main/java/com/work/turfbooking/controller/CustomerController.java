package com.work.turfbooking.controller;

import com.work.turfbooking.dto.CustomerDto;
import com.work.turfbooking.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/addCustomer")
    @ApiOperation("Save customer")
    public CustomerDto saveCustomer(@RequestBody CustomerDto customerDto){
        customerService.addCustomer(customerDto);
        return customerDto;
    }

    @GetMapping("/getCustomerByName/{name}")
    @ApiOperation("Get customer by name")
    public List<CustomerDto> getCustomerByName(@PathVariable String name){

        List<CustomerDto> customerDtoList = customerService.getCustomerByName(name);
        return customerDtoList;

    }
    @GetMapping("/getCustomerById/{id}")
    public CustomerDto getCustomerById(@PathVariable Integer id){

        CustomerDto customerDto = customerService.getCustomerById(id);
        return customerDto;
    }
    @GetMapping("/getAllCustomers}")
    public List<CustomerDto> getAllCustomers(@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                             @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize){

        List<CustomerDto> customerDtoList = customerService.getAllCustomers(pageNumber,pageSize);
        return customerDtoList;
    }
    @PutMapping("/updateCustomer/{id}")
    public CustomerDto updateCustomer(@PathVariable Integer id,@RequestBody CustomerDto customerDto){

        CustomerDto updatedCustomer = customerService.updateCustomer(id,customerDto);
        return updatedCustomer;
    }
    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable Integer id){

        customerService.deleteCustomer(id);
        return "Successfully deleted";
    }

}
