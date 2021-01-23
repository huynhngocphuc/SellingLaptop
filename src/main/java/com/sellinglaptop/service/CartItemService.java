package com.sellinglaptop.service;

import com.sellinglaptop.dao.CartItemDAO;
import com.sellinglaptop.model.CartItemModel;

import javax.inject.Inject;
import java.util.List;

public class CartItemService {
    @Inject
    CartItemDAO cartItemDAO;
    public List<CartItemModel> findAll(){
        return cartItemDAO.findAll();
    }

    public List<CartItemModel> findByCartId(int cart_id){
        return cartItemDAO.findByCartId(cart_id);
    }

    public int save(CartItemModel cartItemModel) {
        return cartItemDAO.save(cartItemModel);
    }
}
