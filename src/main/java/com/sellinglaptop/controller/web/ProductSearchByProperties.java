package com.sellinglaptop.controller.web;


import com.sellinglaptop.model.BrandModel;
import com.sellinglaptop.model.ProductModel;
import com.sellinglaptop.service.BrandService;
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


@WebServlet(urlPatterns = {"/client-searchByProperties"})
public class ProductSearchByProperties extends HttpServlet {
    @Inject
    ProductService productService;
    @Inject
    BrandService brandService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer brandid= (req.getParameter("brand_id")==null?null:Integer.parseInt(req.getParameter("brand_id"))) ;
        Integer startprice =(req.getParameter("startPrice")==null ?null:Integer.parseInt(req.getParameter("startPrice")) );
        Integer endprice =(req.getParameter("endPrice")==null?null:Integer.parseInt(req.getParameter("endPrice")));
        HttpSession sessProperty = req.getSession();
        if (brandid != null)
            sessProperty.setAttribute("brand_id",brandid);
        if (startprice !=null && endprice !=null) {
            sessProperty.setAttribute("start_price",startprice);
            sessProperty.setAttribute("end_price",endprice);
        }
        Integer brand_id =(sessProperty.getAttribute("brand_id")==null ? null : (int)sessProperty.getAttribute("brand_id"));
        Integer cate_id = (sessProperty.getAttribute("cate_id")==null ? null :(int)sessProperty.getAttribute("cate_id")) ;
        Integer detail_cate_id = (sessProperty.getAttribute("detail_cate_id")==null ? null: (int)sessProperty.getAttribute("detail_cate_id")) ;
        Integer start_price = (sessProperty.getAttribute("start_price")==null?0:(int)sessProperty.getAttribute("start_price"));
        Integer end_price = (sessProperty.getAttribute("end_price")==null?900000000:(int)sessProperty.getAttribute("end_price"));
        String key_word = (String) sessProperty.getAttribute("key_word");

        Integer page = (req.getParameter("page")==null?1:Integer.parseInt(req.getParameter("page"))) ; //tmp

        List<Object> values = new ArrayList<>();
        values.add(0,brand_id);
        values.add(1,cate_id);
        values.add(2,detail_cate_id);
        values.add(3,start_price);
        values.add(4,end_price);
        values.add(5,key_word);

        int litmit=9;
        int offset = (page-1) * litmit;

        Object[] obj = productService.searchByProperties(values,offset,litmit);

        int numOfProducts=(int)obj[1];

        int numOfPages = 0;
        if (numOfProducts/litmit==(float)numOfProducts/litmit){
            numOfPages = numOfProducts/litmit;
        }
        else{
            numOfPages = numOfProducts/litmit+1;
        }

        List<ProductModel> products = (List<ProductModel>) obj[0];
        req.setAttribute("numOfPages",numOfPages);
        req.setAttribute("proList",products);
        List<BrandModel> brands = brandService.findAll();
        req.setAttribute("brands",brands);

        req.getRequestDispatcher("/views/web/shop.jsp").forward(req, resp);


    }
}

