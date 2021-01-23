package com.sellinglaptop.service;

import com.sellinglaptop.dao.CartDAO;
import com.sellinglaptop.model.CartModel;

import javax.inject.Inject;
import java.util.List;

public class CartService {

    @Inject
    CartDAO cartDAO;
    public List<CartModel> findAll(){
        return cartDAO.findAll();
    }
    public List<CartModel> findByUserId(int user_id){
        return cartDAO.findByUserId(user_id);
    }
    public int save(CartModel cartModel){
        return cartDAO.save(cartModel);
    }
    public void delete(int id) {
        cartDAO.delete(id);
    }
    public CartModel findByCartId(int id) {
        return cartDAO.findOne(id);
    }
    public void update(CartModel cart) {
         cartDAO.update(cart);
    }
}
