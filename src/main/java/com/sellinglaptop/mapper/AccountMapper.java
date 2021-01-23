package com.sellinglaptop.mapper;

import com.sellinglaptop.model.AccountModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements IRowMapper<AccountModel> {

    @Override
    public AccountModel mapRow(ResultSet resultSet) {
        AccountModel account = new AccountModel();
        try{
            account.setId(resultSet.getInt("id"));
            account.setUsername(resultSet.getString("username"));
            account.setPassword(resultSet.getString("password"));
            account.setEmail(resultSet.getString("email"));
            account.setPhonenumber(resultSet.getString("phonenumber"));
            account.setAddress(resultSet.getString("address"));
            account.setAvatar(resultSet.getString("avatar"));
            account.setRoleId(resultSet.getInt("role_id"));

        }catch (SQLException e){
            e.printStackTrace();
            return  null;
        }
        return account;
    }
}
