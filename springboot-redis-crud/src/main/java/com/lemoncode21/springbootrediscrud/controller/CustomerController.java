package com.lemoncode21.springbootrediscrud.controller;

import com.lemoncode21.springbootrediscrud.model.Customer;
import com.lemoncode21.springbootrediscrud.repository.CustomerRepository;
import com.lemoncode21.springbootrediscrud.respone.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // save
    @PostMapping("/save")
    public ResponseHandler save(@RequestBody Customer customer){
        try{
            this.customerRepository.save(customer);
            return  new ResponseHandler(HttpStatus.OK.toString(), "Success saved data!");
        }catch (Exception e ){
            return  new ResponseHandler(HttpStatus.MULTI_STATUS.toString(), e.getMessage());
        }
    }

    // get all
    @GetMapping
    public ResponseHandler getAll(){
        try{
            List<Customer> customerList = this.customerRepository.findAll();
            return  new ResponseHandler(HttpStatus.OK.toString(), "Success get all data!",customerList);
        }catch (Exception e){
            return  new ResponseHandler(HttpStatus.MULTI_STATUS.toString(), e.getMessage());
        }
    }

    // get by id
    @GetMapping("/{id}")
    public ResponseHandler getById(@PathVariable Long id){
        try{
            Customer customer = this.customerRepository.findCustomerById(id);
            return  new ResponseHandler(HttpStatus.OK.toString(), "Success get by id data!",customer);
        }catch (Exception e){
            return  new ResponseHandler(HttpStatus.MULTI_STATUS.toString(), e.getMessage());
        }
    }

    // update
    @PutMapping("/{id}")
    public ResponseHandler update(@PathVariable Long id,@RequestBody Customer customer){
        try{
            Customer _customer = this.customerRepository.findCustomerById(id);
            _customer.setName(customer.getName());
            _customer.setAge(customer.getAge());
            this.customerRepository.save(customer);

            return  new ResponseHandler(HttpStatus.OK.toString(), "Success update data!");
        }catch (Exception e){
            return  new ResponseHandler(HttpStatus.MULTI_STATUS.toString(), e.getMessage());
        }
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseHandler delete(@PathVariable Long id){
        try{
            this.customerRepository.deleteCustomer(id);
            return  new ResponseHandler(HttpStatus.OK.toString(), "Success delete data!");
        }catch (Exception e){
            return  new ResponseHandler(HttpStatus.MULTI_STATUS.toString(), e.getMessage());
        }
    }
}
