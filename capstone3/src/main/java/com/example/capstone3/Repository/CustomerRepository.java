package com.example.capstone3.Repository;

import com.example.capstone3.Model.Customer;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findCustomerByEmail(String email);


    Customer findCustomerById(Integer id);


}
