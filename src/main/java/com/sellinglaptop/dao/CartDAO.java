package com.sellinglaptop.dao;

import com.sellinglaptop.mapper.CartMapper;
import com.sellinglaptop.model.CartModel;

import java.util.List;

public class CartDAO extends GenericDAO<CartModel> {

    public CartModel findOne(int id){
        String sql="Select * from Cart where id =?";
        List<CartModel> carts = query(sql,new CartMapper(),id);
        return carts.isEmpty() ? null : carts.get(0);
    }
    public List<CartModel> findByUserId(int user_id){
        String sql="Select * from Cart where user_id =?";
        List<CartModel> carts = query(sql,new CartMapper(),user_id);
        return carts.isEmpty() ? null : carts;
    }
    public List<CartModel> findAll(){
        String sql="Select * from Cart";
        List<CartModel> carts = query(sql,new CartMapper());
        return carts.isEmpty() ? null : carts;
    }
    public int save(CartModel cartModel){
        if (cartModel.getUserID() > 0) {
            String sql="insert into Cart(user_id,total_price) values(?,?)";
            return insert(sql,cartModel.getUserID(),cartModel.getTotalPrice());
        } else if(cartModel.getCustomerID() > 0) {
            String sql="insert into Cart(customer_id,total_price) values(?,?)";
            return insert(sql,cartModel.getCustomerID(),cartModel.getTotalPrice());
        } else
            return -1;

    }
    public void update(CartModel cartModel){
        String sql="update Cart set total_price=?, status=?, option_pay=? where id=?";
        update(sql,cartModel.getTotalPrice() ,cartModel.getStatus(),cartModel.getOptionPay(),cartModel.getId());
    }

    public void delete(int id){
        String sql1="delete from Cartitem where cart_id=?";
        String sql2="delete from Cart where id=?";
        update(sql1,id);
        update(sql2,id);
    }
}
