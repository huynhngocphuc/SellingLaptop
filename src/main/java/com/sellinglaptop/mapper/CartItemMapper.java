package com.sellinglaptop.mapper;

import com.sellinglaptop.model.CartItemModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartItemMapper implements IRowMapper<CartItemModel> {
    @Override
    public CartItemModel mapRow(ResultSet resultSet) {
        CartItemModel cartItemModel = new CartItemModel();
        try{
            cartItemModel.setCartId(resultSet.getInt("cart_id"));
            cartItemModel.setProductId(resultSet.getInt("product_id"));
            cartItemModel.setQuantity(resultSet.getInt("quantity"));
            cartItemModel.setUnitPrice(resultSet.getDouble("unit_price"));
        }
        catch (SQLException e){
            return null;
        }
        return cartItemModel;
    }
}
