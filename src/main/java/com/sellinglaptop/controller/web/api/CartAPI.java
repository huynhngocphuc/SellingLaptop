package com.sellinglaptop.controller.web.api;

import com.sellinglaptop.model.CartItemModel;
import com.sellinglaptop.model.CartModel;
import com.sellinglaptop.model.ProductModel;
import com.sellinglaptop.service.ProductService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api-user-cart"})
public class CartAPI extends HttpServlet {
    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        int quantity=1;
//        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println(req.getReader().readLine());
        int id = Integer.parseInt(req.getReader().readLine());
        ProductModel productModel = productService.findOne(id);
        if (productModel != null){
            HttpSession session = req.getSession();
            if (session.getAttribute("order")==null) {
                CartModel cartModel = new CartModel();
                List<CartItemModel> cartItemModelList = new ArrayList<CartItemModel>();
                CartItemModel cartItemModel = new CartItemModel();

                cartItemModel.setQuantity(quantity);
                cartItemModel.setProductId(id);
                cartItemModel.setProduct(productModel);
                cartItemModel.setUnitPrice(productModel.getPrice());

                cartItemModelList.add(cartItemModel);

                cartModel.setItemModelList(cartItemModelList);

                session.setAttribute("order",cartModel);
            } else {
                CartModel cartModel =  (CartModel) session.getAttribute("order");
                List<CartItemModel> cartItemModelList = cartModel.getItemModelList();
                boolean check=false;
                for(CartItemModel item : cartItemModelList) {
                    if (item.getProductId()==id){
                        item.setQuantity(item.getQuantity()+quantity);
                        check=true;
                    }
                }
                if (check==false) {
                    CartItemModel cartItemModel = new CartItemModel();

                    cartItemModel.setQuantity(quantity);
                    cartItemModel.setProductId(id);
                    cartItemModel.setProduct(productModel);
                    cartItemModel.setUnitPrice(productModel.getPrice());
                    cartItemModelList.add(cartItemModel);
                }
                cartModel.setItemModelList(cartItemModelList);
                session.setAttribute("order",cartModel);
            }

        }

        String url ="/client-cart-list";
        resp.sendRedirect(req.getContextPath()+url);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        int quantity=1;
        int id = Integer.parseInt(req.getReader().readLine());
        ProductModel productModel = productService.findOne(id);
        if (productModel != null){
            HttpSession session = req.getSession();
            if (session.getAttribute("order")==null) {
                CartModel cartModel = new CartModel();
                List<CartItemModel> cartItemModelList = new ArrayList<CartItemModel>();
                CartItemModel cartItemModel = new CartItemModel();

                cartItemModel.setQuantity(quantity);
                cartItemModel.setProductId(id);
                cartItemModel.setProduct(productModel);
                cartItemModel.setUnitPrice(productModel.getPrice());

                cartItemModelList.add(cartItemModel);

                cartModel.setItemModelList(cartItemModelList);

                session.setAttribute("order",cartModel);
            } else {
                CartModel cartModel =  (CartModel) session.getAttribute("order");
                List<CartItemModel> cartItemModelList = cartModel.getItemModelList();
                boolean check=false;
                for(CartItemModel item : cartItemModelList) {
                    if (item.getProductId()==id){
                        item.setQuantity(item.getQuantity()+quantity);
                        check=true;
                    }
                }
                if (check==false) {
                    CartItemModel cartItemModel = new CartItemModel();

                    cartItemModel.setQuantity(quantity);
                    cartItemModel.setProductId(id);
                    cartItemModel.setProduct(productModel);
                    cartItemModel.setUnitPrice(productModel.getPrice());

                    cartItemModelList.add(cartItemModel);
                }
                cartModel.setItemModelList(cartItemModelList);
                session.setAttribute("order",cartModel);
            }

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type=req.getParameter("type");
        System.out.println(type);
        int productId = Integer.parseInt(req.getReader().readLine());
        HttpSession session = req.getSession();
        CartModel cartModel =  (CartModel) session.getAttribute("order");
        List<CartItemModel> cartItemModelList = cartModel.getItemModelList();

        for(CartItemModel item : cartItemModelList) {
            if (item.getProductId()==productId){
                if (type.equals("add")) {
                    item.setQuantity(item.getQuantity() + 1);
                } else if (type.equals("sub") && item.getQuantity() > 1){
                    item.setQuantity(item.getQuantity() - 1);
                } else if (type.equals("del")){
                    cartItemModelList.remove(item);
                }

            }
        }
        cartModel.setItemModelList(cartItemModelList);
        session.setAttribute("order",cartModel);

        String url ="/client-cart-list";
        resp.sendRedirect(req.getContextPath()+url);
    }
}
