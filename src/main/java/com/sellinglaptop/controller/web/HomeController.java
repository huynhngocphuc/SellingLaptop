package com.sellinglaptop.controller.web;

import com.sellinglaptop.Cons.AccountRegister;
import com.sellinglaptop.Cons.Message;
import com.sellinglaptop.model.AccountModel;
import com.sellinglaptop.service.AccountService;
import com.sellinglaptop.service.CategoryService;
import com.sellinglaptop.service.DetailCategoryService;
import com.sellinglaptop.service.ProductService;
import com.sellinglaptop.utils.JavaMailUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet(urlPatterns = {"/trang-chu","/dang-ky"})
public class HomeController extends HttpServlet {
    @Inject
    private CategoryService categoryService;

    @Inject
    private AccountService accountService;

    @Inject
    private DetailCategoryService detailCategoryService;

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type= req.getParameter("action");
        if (type.equals("register")) {

            req.setAttribute("errUserName",Message.errUser);
            req.setAttribute("errEmail",Message.errEmail);
            req.setAttribute("errPhone",Message.errPhone);
            req.setAttribute("errPassword",Message.errPassword);
            req.setAttribute("errAddress",Message.errAddress);
            req.setAttribute("accountRegister",AccountRegister.getAccountRegister());
            RequestDispatcher rd = req.getRequestDispatcher("views/web/register.jsp");
            rd.forward(req,resp);
        }
        else if (type.equals("dangnhap")) {
            RequestDispatcher rd = req.getRequestDispatcher("views/web/login.jsp");
            rd.forward(req,resp);
        }
        else if (type.equals("home")) {
            AccountRegister.reset();
            Message.reset();
            RequestDispatcher rd = req.getRequestDispatcher("views/web/index.jsp");
            rd.forward(req,resp);
        }

    }

//    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email =req.getParameter("emailReset");
        String username= req.getParameter("userName");
        String msg="";
        AccountModel accountModel=null;
        if (email == null)
        {
            msg="Vui lòng nhập email";
        }
        else {
            accountModel = accountService.findByUsername(username);

//            accountModel = accountService.getUser(email);
            if (accountModel == null ) {
                msg="Tài khoản không tồn tại";
            } else if (!accountModel.getEmail().equals(email)) {
                msg="Email không chính xác";
            } else {
                Random random = new Random();
                Integer pass = random.nextInt(899999) + 100000;
                accountModel.setPassword(pass.toString());
                accountService.update(accountModel);
                String content = "Mật khẩu mới của bạn là: " + accountModel.getPassword();
                try {
                    JavaMailUtil.sendMail(email, "Lấy lại mật khẩu", content);
                    msg = "Mật khẩu mới đã được gửi vào gmail của bạn.";
                } catch (Exception e) {
                    msg = "Thao tác thất bại, vui lòng thử lại.";
                    e.printStackTrace();
                }
            }
        }

        req.setAttribute("msg",msg);
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/web/login.jsp");
        dispatcher.forward(req, resp);
    }
}
