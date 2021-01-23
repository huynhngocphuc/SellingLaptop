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

@WebServlet(urlPatterns = {"/admin-home","/dang-nhap"})
public class HomeController extends HttpServlet {
    @Inject
    private AccountService accountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        if (action!=null && action.equals("logout")){
            session.removeAttribute("loginName");
            session.removeAttribute("order");
            RequestDispatcher rd = req.getRequestDispatcher("views/web/login.jsp");
            rd.forward(req,resp);
        }
        else{
            if (session.getAttribute("loginName")==null){
                RequestDispatcher rd = req.getRequestDispatcher("views/web/login.jsp");
                rd.forward(req,resp);
            }
            else  {
                RequestDispatcher rd = req.getRequestDispatcher("views/admin/view/index.jsp");
                rd.forward(req,resp);
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        RequestDispatcher rd;
        if (action!=null && action.equals("login")){
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            AccountModel user=new AccountModel();
            user.setUsername(username);
            user.setPassword(password);
            AccountModel accountModel = accountService.findByUsernameAndPassword(user);
            if (accountModel != null){

                HttpSession session = req.getSession();
                session.removeAttribute("order");
                session.setAttribute("loginName",username);
                if (accountModel.getRoleId()==1){
                    rd = req.getRequestDispatcher("views/admin/view/index.jsp");
                    rd.forward(req,resp);
                }
                else if (accountModel.getRoleId()==0){
                    rd = req.getRequestDispatcher("views/web/index.jsp");
                    rd.forward(req,resp);
                }
            }
            else {
                rd = req.getRequestDispatcher("views/web/login.jsp");
                rd.forward(req,resp);
            }

        }
        else{
            rd = req.getRequestDispatcher("views/web/index.jsp");
            rd.forward(req,resp);
        }

    }
}
