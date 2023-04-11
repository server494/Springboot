package com.work.turfbooking.service;

import com.work.turfbooking.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto addCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(Integer id,CustomerDto customerDto);

    List<CustomerDto> getCustomerByName(String name);

    CustomerDto getCustomerById(Integer id);

    void deleteCustomer(Integer id);

    List<CustomerDto> getAllCustomers(Integer pageNumber,Integer pageSize);

}
