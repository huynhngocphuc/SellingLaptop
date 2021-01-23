package com.sellinglaptop.dao;

import com.sellinglaptop.mapper.CartItemMapper;
import com.sellinglaptop.model.CartItemModel;

import java.util.List;

public class CartItemDAO extends GenericDAO<CartItemModel>{
    public List<CartItemModel> findAll(){
        String sql="Select * from CartItem";
        List<CartItemModel> cartItems = query(sql,new CartItemMapper());
        return cartItems.isEmpty() ? null : cartItems;
    }
    public List<CartItemModel> findByCartId(int cart_id){
        String sql="Select * from CartItem where cart_id=?";
        List<CartItemModel> cartItems = query(sql,new CartItemMapper(),cart_id);
        return cartItems.isEmpty() ? null : cartItems;
    }
    public int save(CartItemModel cartItemModel){
        String sql="insert into cartitem values (?,?,?,?)";
        return insert(sql,cartItemModel.getCartId(),cartItemModel.getProductId(),cartItemModel.getQuantity(),cartItemModel.getUnitPrice());
    }
}
