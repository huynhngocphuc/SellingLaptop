package com.sellinglaptop.controller.admin;

import com.sellinglaptop.model.BrandModel;
import com.sellinglaptop.model.DetailCategoryModel;
import com.sellinglaptop.model.ProductModel;
import com.sellinglaptop.service.BrandService;
import com.sellinglaptop.service.CategoryService;
import com.sellinglaptop.service.DetailCategoryService;
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

@WebServlet(urlPatterns = {"/admin-product-list"})
public class ProductController extends HttpServlet {

    @Inject
    ProductService productService;

    @Inject
    CategoryService categoryService;

    @Inject
    DetailCategoryService detailCategoryService;

    @Inject
    BrandService brandService;

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
                req.setAttribute("detailCateList",detailCategoryModelList);
                req.setAttribute("proList",productModelList);
                for(int i=0;i<productModelList.size();i++){
                    System.out.println(productModelList.get(i).getImage());
                }
                url ="views/admin/view/list-product.jsp";

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
            List<BrandModel> brands = brandService.findAll();
            req.setAttribute("brands",brands);
            RequestDispatcher rd = req.getRequestDispatcher(url);
            rd.forward(req,resp);
        }

    }
}
