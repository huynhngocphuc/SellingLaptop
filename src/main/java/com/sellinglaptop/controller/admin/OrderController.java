package com.sellinglaptop.controller.admin;

import com.sellinglaptop.model.*;
import com.sellinglaptop.service.*;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-order-list"})
public class OrderController extends HttpServlet {

    @Inject
    ProductService productService;

    @Inject
    CategoryService categoryService;

    @Inject
    DetailCategoryService detailCategoryService;

    @Inject
    AccountService accountService;
    @Inject
    CartItemService cartItemService;

    @Inject
    CartService cartService;

    @Inject
    CustomerService customerService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        if (session.getAttribute("loginName")==null){
            RequestDispatcher rd = req.getRequestDispatcher("views/web/login.jsp");
            rd.forward(req,resp);
        }
        else {
            String type= req.getParameter("type");
            String url ="";
            //ProductService productService =new ProductService();
            if ( type.equals("list")){
                List<ProductModel> productModelList= productService.findAll();
                List<DetailCategoryModel> detailCategoryModelList = detailCategoryService.findAll();
                List<AccountModel> accountModelList= accountService.findAll();
                List<CartItemModel> cartItemModelList =  cartItemService.findAll();
                if (cartItemModelList != null) {
                    for (CartItemModel item : cartItemModelList) {
                        for (ProductModel pro : productModelList) {
                            if (item.getProductId() == pro.getId()) {
                                item.setProduct(pro);
                            }
                        }
                    }
                }


                List<CartModel> cartModelList =cartService.findAll();
                List<CustomerModel> customerList = customerService.findAll();
                if (cartItemModelList !=null) {
                    for (CartModel cart : cartModelList) {
                        List<CartItemModel> lstItem = new ArrayList<CartItemModel>();
                        if (cartItemModelList != null) {
                            for (CartItemModel item : cartItemModelList) {
                                if (cart.getId() == item.getCartId()) {
                                    lstItem.add(item);
                                }
                                cart.setItemModelList(lstItem);
                            }
                        }

                    }
                }


                req.setAttribute("userList",accountModelList);
                req.setAttribute("customerList",customerList);
                req.setAttribute("detailCateList",detailCategoryModelList);
                req.setAttribute("proList",productModelList);
                req.setAttribute("cartItemList",cartItemModelList);
                req.setAttribute("cartList",cartModelList);
                url ="views/admin/view/list-order.jsp";

            }
            else if(type.equals("add")){
                List<DetailCategoryModel> detailCategories = detailCategoryService.findAll();

                req.setAttribute("detailCategories", detailCategories);
                url ="views/admin/view/add-product.jsp";
            }
            else if(type.equals("edit")){
                String id = req.getParameter("id");
                ProductModel product = productService.findOne(Integer.parseInt(id));
                List<DetailCategoryModel> detailCategories = detailCategoryService.findAll();

                req.setAttribute("detailCategories", detailCategories);
                System.out.println("Size of list"+ detailCategories.size());
                req.setAttribute("product", product);

                url ="views/admin/view/edit-product.jsp";
            }
            RequestDispatcher rd = req.getRequestDispatcher(url);
            rd.forward(req,resp);
        }

    }
}
