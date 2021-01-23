package com.sellinglaptop.dao;

import com.sellinglaptop.mapper.CustomerMapper;
import com.sellinglaptop.model.CustomerModel;

import java.util.List;

public class CustomerDAO extends GenericDAO<CustomerModel> {
    public int save(CustomerModel customerModel){
        String sql="insert into Customer(fullname,email,phonenumber,address) values(?,?,?,?)";
        return insert(sql,customerModel.getFullname(),customerModel.getEmail(),customerModel.getPhonenumber(),customerModel.getAddress());
    }
    public List<CustomerModel> findAll() {
        String sql="select * from Customer";
        List<CustomerModel> customers = query(sql,new CustomerMapper());
        return customers.isEmpty() ? null : customers;
    }
}
