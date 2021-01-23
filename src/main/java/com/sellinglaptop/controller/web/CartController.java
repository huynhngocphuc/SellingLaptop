package com.sellinglaptop.controller.web;

import com.sellinglaptop.model.AccountModel;
import com.sellinglaptop.model.CartItemModel;
import com.sellinglaptop.model.CartModel;
import com.sellinglaptop.service.AccountService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/client-cart-list"})
public class CartController  extends HttpServlet {
    @Inject
    private AccountService accountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CartModel cartModel =  (CartModel) session.getAttribute("order");
        String userName =(String) session.getAttribute("loginName");
        double totalPrice =0;
        if (userName != null) {
            AccountModel accountModel = accountService.findByUsername(userName);
            req.setAttribute("accountModel",accountModel);
        }
        if (cartModel != null) {
            List<CartItemModel> cartItemModelList = cartModel.getItemModelList();
            req.setAttribute("cartItemModelList",cartItemModelList);

            for(CartItemModel item: cartItemModelList) {
                totalPrice += item.getUnitPrice() * item.getQuantity();
            }
            cartModel.setTotalPrice(totalPrice);
        }
        req.setAttribute("totalPrice",totalPrice);

        String url ="views/web/cart.jsp";
        RequestDispatcher rd = req.getRequestDispatcher(url);
        rd.forward(req,resp);
    }
}
