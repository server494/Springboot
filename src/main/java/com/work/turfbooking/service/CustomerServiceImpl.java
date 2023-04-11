package com.work.turfbooking.service;

import com.work.turfbooking.dto.CustomerDto;
import com.work.turfbooking.entity.Customer;
import com.work.turfbooking.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {

        Customer customer = dtoCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerToDto(savedCustomer);
    }


    @Override
    public CustomerDto updateCustomer(Integer id, CustomerDto customerDto) {

        Customer customer = customerRepository.findById(id).get();
        customer.setName(customerDto.getName());
        customer.setAddress(customerDto.getAddress());
        customerRepository.save(customer);
        CustomerDto updatedCustomer=customerToDto(customer);
        return updatedCustomer;
    }

    @Override
    public List<CustomerDto> getCustomerByName(String name) {
        List<Customer> customerList = customerRepository.getCustomersByName(name);
        List<CustomerDto> customerDtoList = customerList.stream()
                .map((customer -> modelMapper.map(customer,CustomerDto.class)))
                .collect(Collectors.toList());
        return customerDtoList;
    }

    @Override
    public CustomerDto getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).get();
        CustomerDto customerDto = customerToDto(customer);
        return customerDto;
    }

    @Override
    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findById(id).get();
        customerRepository.delete(customer);

    }

    @Override
    public List<CustomerDto> getAllCustomers(Integer pageNumber, Integer pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        Page<Customer> page = customerRepository.findAll(pageRequest);
        List<Customer> pagesList=page.getContent();

        List<CustomerDto> customersList = pagesList.stream()
                .map((customer -> modelMapper.map(customer, CustomerDto.class)))
                .collect(Collectors.toList());
        return customersList;
    }

    private Customer dtoCustomer(CustomerDto customerDto){

        Customer customer = modelMapper.map(customerDto,Customer.class);
        return customer;

    }
    private CustomerDto customerToDto(Customer customer){

        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        return customerDto;
    }
}
