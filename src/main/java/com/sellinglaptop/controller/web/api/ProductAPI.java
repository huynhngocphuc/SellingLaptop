package com.sellinglaptop.controller.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sellinglaptop.model.CartItemModel;
import com.sellinglaptop.service.ProductService;
import com.sellinglaptop.utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-user-product"})
public class ProductAPI extends HttpServlet {
    @Inject
    private ProductService productService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int quantity=Integer.parseInt(req.getParameter("quantity")) ;
        String action =req.getParameter("action");
        if (action.equals("add")) {
            System.out.println("Add");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int quantity=Integer.parseInt(req.getParameter("quantity")) ;
        String action =req.getParameter("action");
        if (action.equals("add")) {
            System.out.println("Add");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        CartItemModel item = HttpUtil.of(req.getReader()).toModel(CartItemModel.class);
        String action =req.getParameter("action");
        if (action.equals("add")) {
            item.setQuantity(item.getQuantity()+1);
        } else if (action.equals("sub") && item.getQuantity() > 0) {
            item.setQuantity(item.getQuantity()-1);
        }

        resp.sendRedirect(req.getContextPath()+ "/client-product-list?type=detail_product&id="+item.getProductId()+"&quantity="+item.getQuantity());
//        mapper.writeValue(resp.getOutputStream(),item);

    }
}
