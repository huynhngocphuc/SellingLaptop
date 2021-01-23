package com.sellinglaptop.controller.web;

import com.sellinglaptop.model.AccountModel;
import com.sellinglaptop.model.CartItemModel;
import com.sellinglaptop.model.CartModel;
import com.sellinglaptop.model.ProductModel;
import com.sellinglaptop.service.AccountService;
import com.sellinglaptop.service.CartItemService;
import com.sellinglaptop.service.CartService;
import com.sellinglaptop.service.ProductService;

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

@WebServlet(urlPatterns = {"/account-manager"})
public class AccountController extends HttpServlet {
    @Inject
    private AccountService accountService;
    @Inject
    private CartService cartService;
    @Inject
    private CartItemService cartItemService;
    @Inject
    private ProductService productService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username=(String) session.getAttribute("loginName");
        if (username!=null) {
            AccountModel accountModel= accountService.findByUsername(username);
            List<CartModel> carts = cartService.findByUserId(accountModel.getId());
            List<ProductModel> products = productService.findAll();
            if (carts !=null) {
                for(CartModel cart : carts) {
                    List<CartItemModel> cartItemModelList = cartItemService.findByCartId(cart.getId());
                    if (cartItemModelList !=null) {
                        for(CartItemModel item: cartItemModelList) {
                            for(ProductModel pro : products) {
                                if ( item.getProductId()==pro.getId()) {
                                    item.setProduct(pro);
                                }
                            }
                        }
                        cart.setItemModelList(cartItemModelList);
                    }
                }
            }

            req.setAttribute("carts",carts);
            req.setAttribute("accountModel",accountModel);
            RequestDispatcher rd = req.getRequestDispatcher("/views/web/checkout.jsp");
            rd.forward(req,resp);
        }
        else {
            RequestDispatcher rd = req.getRequestDispatcher("/views/web/index.jsp");
            rd.forward(req,resp);
        }
    }
}
