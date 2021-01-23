package com.sellinglaptop.controller.web.api;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sellinglaptop.model.AccountModel;
import com.sellinglaptop.model.CartItemModel;
import com.sellinglaptop.model.CustomerModel;
import com.sellinglaptop.model.CartModel;
import com.sellinglaptop.service.AccountService;
import com.sellinglaptop.service.CartItemService;
import com.sellinglaptop.service.CartService;
import com.sellinglaptop.service.CustomerService;
import com.sellinglaptop.utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-user-order"})
public class OrderAPI extends HttpServlet {
    @Inject
    private AccountService accountService;
    @Inject
    private CartService cartService;
    @Inject
    private CartItemService cartItemService;
    @Inject
    private CustomerService customerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session = req.getSession();
        CartModel cartModel=(CartModel) session.getAttribute("order");
        String isOnline =(String) session.getAttribute("isOnline");
        String userName = (String) session.getAttribute("loginName");
        if (cartModel != null) {
            if (userName == null) {
                CustomerModel customerModel = HttpUtil.of(req.getReader()).toModel(CustomerModel.class);
                session.setAttribute("customer",customerModel);

                int cus_id = customerService.save(customerModel);
                cartModel.setCustomerID(cus_id);
                int cart_id = cartService.save(cartModel);
                for(CartItemModel item: cartModel.getItemModelList()) {
                    item.setCartId(cart_id);
                    cartItemService.save(item);
                }
/*                RequestDispatcher rd =req.getRequestDispatcher(req.getContextPath()+"/views/web/done-order.jsp");
                rd.forward(req,resp);*/
                mapper.writeValue(resp.getOutputStream(),customerModel);
            }
            else {
                AccountModel accountModel = accountService.findByUsername(userName);
                session.setAttribute("user",accountModel);
                cartModel.setUserID(accountModel.getId());
                int cart_id = cartService.save(cartModel);
                for(CartItemModel item: cartModel.getItemModelList()) {
                    item.setCartId(cart_id);
                    cartItemService.save(item);
                }
                /*RequestDispatcher rd =req.getRequestDispatcher("/views/web/done-order.jsp");
                rd.forward(req,resp);*/
                if (isOnline !=null) {
                    resp.sendRedirect(req.getContextPath()+"/paySuccess");
                } else {
                    mapper.writeValue(resp.getOutputStream(),accountModel);
                }


                System.out.println("Mua hang thanh cong");
            }

        }
/*        RequestDispatcher rd = req.getRequestDispatcher("views/web/continue-order.jsp");
        rd.forward(req,resp);*/

    }
}
