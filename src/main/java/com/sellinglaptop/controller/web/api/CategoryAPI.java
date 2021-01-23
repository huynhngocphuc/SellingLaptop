package com.sellinglaptop.controller.web.api;

import com.sellinglaptop.model.ProductModel;
import com.sellinglaptop.service.ProductService;
import com.sellinglaptop.utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-user-category"})
public class CategoryAPI extends HttpServlet {
    @Inject
    private ProductService productService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json"); //
        // Convert from type json to Model
        //CategoryModel categoryModel= HttpUtil.of(req.getReader()).toModel(CategoryModel.class);
        ProductModel productModel = HttpUtil.of(req.getReader()).toModel(ProductModel.class);
        productModel = productService.save(productModel);
        System.out.println(productModel);
    }
}
