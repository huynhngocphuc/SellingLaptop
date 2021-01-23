package com.sellinglaptop.controller.web;

import com.sellinglaptop.model.CartItemModel;
import com.sellinglaptop.model.DetailCategoryModel;
import com.sellinglaptop.model.ProductModel;
import com.sellinglaptop.service.CategoryService;
import com.sellinglaptop.service.DetailCategoryService;
import com.sellinglaptop.service.ProductService;
import com.sellinglaptop.utils.HttpUtil;

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

@WebServlet(urlPatterns = "/list-laptop")
public class IndexController extends HttpServlet {
        @Inject
        ProductService productService;
        @Inject
        CategoryService categoryService;
        @Inject
        DetailCategoryService detailCategoryService;
        @Override
        protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            int offset = 0;
            int numOfPages = 0;
            int limit = 3;
            String type = req.getParameter("type");
            String url = "";
            if (type.equals("list")) {
                // Reset session
                HttpSession sessProperty = req.getSession();
                sessProperty.setAttribute("brand_id", null);
                sessProperty.setAttribute("cate_id", null);
                sessProperty.setAttribute("detail_cate_id", null);
                sessProperty.setAttribute("start_price", 0);
                sessProperty.setAttribute("end_price", 900000000);
                sessProperty.setAttribute("key_word", null);
                // End reset session


                int currentPage = Integer.parseInt(req.getParameter("page"));
                offset = (currentPage - 1) * limit;
                List<ProductModel> productModelList = productService.findAll();
                List<ProductModel> products = productService.findAllByPage(0, 6);
                List<ProductModel> products1 = productService.findByDetailCategoryAndPage(1,0, 3);
                List<ProductModel> products2 = productService.findByDetailCategoryAndPage(2,1, 3);
                List<ProductModel> products3 = productService.findByDetailCategoryAndPage(3,0, 3);

                req.setAttribute("type", type);
                req.setAttribute("currentPage", currentPage);
                req.setAttribute("numOfPages", numOfPages);
                req.setAttribute("proList", products);
                req.setAttribute("proList1", products1);
                req.setAttribute("proList2", products2);
                req.setAttribute("proList3", products3);
                url = "views/web/index.jsp";
            } else if (type.equals("category")) {

                int currentPage = Integer.parseInt(req.getParameter("page"));
                offset = (currentPage - 1) * limit;

                int cate_id = Integer.parseInt(req.getParameter("cate_id"));
                List<ProductModel> productModelList = productService.findByCategory(cate_id);
                List<DetailCategoryModel> detailCategoryModelList = detailCategoryService.findAll();
                List<ProductModel> products = productService.findByCategoryAndPage(cate_id, offset, limit);

                // Reset session
                HttpSession sessProperty = req.getSession();
                sessProperty.setAttribute("brand_id", null);
                sessProperty.setAttribute("cate_id", cate_id);
                sessProperty.setAttribute("detail_cate_id", null);
                sessProperty.setAttribute("start_price", 0);
                sessProperty.setAttribute("end_price", 900000000);
                sessProperty.setAttribute("key_word", null);
                // End reset session


                int numOfProducts = 0;
                if (productModelList != null) {
                    numOfProducts = productModelList.size();
                }
                int litmit = 9;
                if (numOfProducts / litmit == (float) numOfProducts / litmit) {
                    numOfPages = numOfProducts / litmit;
                } else {
                    numOfPages = numOfProducts / litmit + 1;
                }
                req.setAttribute("cate_id", cate_id);
                req.setAttribute("type", type);
                req.setAttribute("currentPage", currentPage);
                req.setAttribute("numOfPages", numOfPages);
                req.setAttribute("detailCateList", detailCategoryModelList);
                req.setAttribute("proList", products);
                url = "views/web/index.jsp";


            } else if (type.equals("detail_category")) {
                int currentPage = Integer.parseInt(req.getParameter("page"));
                offset = (currentPage - 1) * limit;
                int detail_cate_id = Integer.parseInt(req.getParameter("detail_cate_id"));
                List<ProductModel> productModelList = productService.findByDetailCategoryAndPage(detail_cate_id, offset, limit);
//            List<DetailCategoryModel> detailCategoryModelList = detailCategoryService.findAll();
                List<ProductModel> products = productService.findByDetailCategoryAndPage(detail_cate_id, offset, limit);

                // Reset session
                HttpSession sessProperty = req.getSession();
                sessProperty.setAttribute("brand_id", null);
                sessProperty.setAttribute("cate_id", null);
                sessProperty.setAttribute("detail_cate_id", detail_cate_id);
                sessProperty.setAttribute("start_price", 0);
                sessProperty.setAttribute("end_price", 900000000);
                sessProperty.setAttribute("key_word", null);
                // End reset session

                int numOfProducts = 0;
                if (productModelList != null) {
                    numOfProducts = productModelList.size();
                }
                int litmit = 9;
                if (numOfProducts / litmit == (float) numOfProducts / litmit) {
                    numOfPages = numOfProducts / litmit;
                } else {
                    numOfPages = numOfProducts / litmit + 1;
                }

                req.setAttribute("type", type);
                req.setAttribute("currentPage", currentPage);
                req.setAttribute("numOfPages", numOfPages);
                req.setAttribute("proList", products);
                req.setAttribute("detail_cate_id", detail_cate_id);
                url = "views/web/index.jsp";
            } else if (type.equals("detail_product")) {
                int id = Integer.parseInt(req.getParameter("id"));
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                ProductModel productModel = productService.findOne(id);
                req.setAttribute("quantity", quantity);
                req.setAttribute("productModel", productModel);
                url = "views/web/product-details.jsp";
            } else if (type.equals("add")) {
                int id = Integer.parseInt(req.getParameter("id"));
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                CartItemModel item = HttpUtil.of(req.getReader()).toModel(CartItemModel.class);
                String action = req.getParameter("action");
                if (action.equals("add")) {
                    item.setQuantity(item.getQuantity() + 1);
                } else if (action.equals("sub") && item.getQuantity() > 0) {
                    item.setQuantity(item.getQuantity() - 1);
                }
            } else if (type.equals("searchByName")) {
                int currentPage = Integer.parseInt(req.getParameter("page"));
                offset = (currentPage - 1) * limit;

                String keyword = req.getParameter("keyword");
                List<ProductModel> productModelList = productService.searchByKeyword(keyword);
                List<ProductModel> products = productService.searchByKeywordAndPage(keyword, offset, limit);

                // Reset session
                HttpSession sessProperty = req.getSession();
                sessProperty.setAttribute("brand_id", null);
                sessProperty.setAttribute("cate_id", null);
                sessProperty.setAttribute("detail_cate_id", null);
                sessProperty.setAttribute("start_price", 0);
                sessProperty.setAttribute("end_price", 900000000);
                sessProperty.setAttribute("key_word", keyword);
                // End reset session


                int numOfProducts = 0;
                if (productModelList != null) {
                    numOfProducts = productModelList.size();
                }
                int litmit = 9;
                if (numOfProducts / litmit == (float) numOfProducts / litmit) {
                    numOfPages = numOfProducts / litmit;
                } else {
                    numOfPages = numOfProducts / litmit + 1;
                }
                req.setAttribute("keyword", keyword);
                req.setAttribute("type", type);
                req.setAttribute("currentPage", currentPage);
                req.setAttribute("numOfPages", numOfPages);
                req.setAttribute("proList", products);
                url = "views/web/index.jsp";
            }
            RequestDispatcher rd = req.getRequestDispatcher(url);
            rd.forward(req, resp);
        }

}
