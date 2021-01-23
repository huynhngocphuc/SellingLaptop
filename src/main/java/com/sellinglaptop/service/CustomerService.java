package com.sellinglaptop.service;

import com.sellinglaptop.dao.CustomerDAO;
import com.sellinglaptop.model.CustomerModel;

import javax.inject.Inject;
import java.util.List;

public class CustomerService {
    @Inject
    private CustomerDAO customerDAO;
    public int save(CustomerModel customerModel) {
        return customerDAO.save(customerModel);
    }
    public List<CustomerModel> findAll() {
        return customerDAO.findAll();
    }
}
