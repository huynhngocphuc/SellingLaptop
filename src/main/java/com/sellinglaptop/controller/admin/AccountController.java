package com.sellinglaptop.controller.admin;


import com.sellinglaptop.model.AccountModel;
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

@WebServlet(urlPatterns = {"/admin-user-list"})
public class AccountController extends HttpServlet {

    @Inject
    private AccountService accountService;

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
                List<AccountModel> accountModelList = accountService.findAll();
                req.setAttribute("userList",accountModelList);
                url="views/admin/view/list-user.jsp";
            }
            else if(type.equals("add")){
                url="views/admin/view/add-user.jsp";
            }
            else if(type.equals("edit")){
                String id = req.getParameter("id");
                AccountModel account = accountService.findOne(Integer.parseInt(id));
                req.setAttribute("user", account);
                url ="views/admin/view/edit-user.jsp";
            }
            RequestDispatcher rd = req.getRequestDispatcher(url);
            rd.forward(req,resp);
        }

    }
}
