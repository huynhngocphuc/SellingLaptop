package com.sellinglaptop.mapper;

import com.sellinglaptop.model.CartModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartMapper implements IRowMapper<CartModel> {
    @Override
    public CartModel mapRow(ResultSet resultSet) {
        CartModel cartModel = new CartModel();
        try{
            cartModel.setId(resultSet.getInt("id"));
            cartModel.setUserID(resultSet.getInt("user_id"));
            cartModel.setTotalPrice(resultSet.getDouble("total_price"));
            cartModel.setBuyDate(resultSet.getDate("buydate"));
            cartModel.setStatus(resultSet.getInt("status"));
            cartModel.setOptionPay(resultSet.getInt("option_pay"));
        }
        catch (SQLException e){
            return null;
        }
        return cartModel;
    }
}
