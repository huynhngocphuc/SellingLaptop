package com.sellinglaptop.mapper;

import com.sellinglaptop.model.CustomerModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper  implements IRowMapper<CustomerModel>{

    @Override
    public CustomerModel mapRow(ResultSet resultSet) {
        CustomerModel customerModel = new CustomerModel();
        try{
            customerModel.setId(resultSet.getInt("id"));
            customerModel.setFullname(resultSet.getString("fullname"));
            customerModel.setEmail(resultSet.getString("email"));
            customerModel.setPhonenumber(resultSet.getString("phonenumber"));
            customerModel.setAddress(resultSet.getString("address"));
        }
        catch (SQLException e){
            return null;
        }
        return customerModel;
    }
}
