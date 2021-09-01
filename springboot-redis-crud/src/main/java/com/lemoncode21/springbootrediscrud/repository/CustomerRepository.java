package com.lemoncode21.springbootrediscrud.repository;

import com.lemoncode21.springbootrediscrud.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    private RedisTemplate redisTemplate;

    public static  final String HAS_KEY = "Customer";

    public void save(Customer customer){
        redisTemplate.opsForHash().put(HAS_KEY,customer.getId(),customer);
    }

    public List<Customer> findAll(){
        return redisTemplate.opsForHash().values(HAS_KEY);
    }

    public Customer findCustomerById(Long id){
        return (Customer) redisTemplate.opsForHash().get(HAS_KEY,id);
    }

    public void deleteCustomer(Long id) {
        redisTemplate.opsForHash().delete(HAS_KEY,id);
    }

}
