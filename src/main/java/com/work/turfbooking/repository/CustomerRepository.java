package com.work.turfbooking.repository;

import com.work.turfbooking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    List<Customer> findByName(String name);

    @Query("SELECT c FROM Customer c  WHERE c.name=:n")
    public List<Customer> getCustomersByName(@Param("n")String name);

}
