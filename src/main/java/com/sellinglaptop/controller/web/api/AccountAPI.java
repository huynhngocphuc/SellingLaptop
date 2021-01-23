package com.sellinglaptop.controller.web.api;


import com.sellinglaptop.Cons.AccountRegister;
import com.sellinglaptop.Cons.Message;
import com.sellinglaptop.model.AccountModel;
import com.sellinglaptop.service.AccountService;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sellinglaptop.utils.JavaMailUtil;

@WebServlet(urlPatterns = {"/api-user-dangky","/api-user-change-password","/api-user-change-Inf"})
public class AccountAPI extends HttpServlet {
    @Inject
    private AccountService accountService;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        AccountModel accountModel = HttpUtil.of(req.getReader()).toModel(AccountModel.class);

       Message.reset();

        AccountRegister.setAccountRegister(accountModel);


        boolean check = false;

        if(accountModel.getUsername().equals(""))
        {

            Message.errUser="Vui long nhap UserName !";
            check = true;
        }
        if(accountModel.getEmail().equals(""))
        {
            Message.errEmail = "Vui long nhap Email !";
            check = true;
        }
        if(accountModel.getPhonenumber().equals(""))
        {
            Message.errPhone = "Vui long nhap SDT";
            check = true;
        }
        if(accountModel.getPassword().equals(""))
        {
            Message.errPassword = "Vui long nhap password";
            check = true;
        }
        else {
            if(!accountModel.getPassword().equals(accountModel.getConfirmation_pwd()))
            {
                Message.errPassword = "Khong trung mat khau";
                check = true;
            }
        }
        if(accountModel.getAddress().equals(""))
        {
            Message.errAddress = "Vui long nhap dia chi!";
            check = true;
        }
        if(check == true)
        {
            return;
        }
        accountModel= accountService.save(accountModel);
        if (accountModel!=null){
            String content="<h3>Đăng ký tài khoản thành công!</h3><br><p>Cảm ơn bạn đã sử dụng dịch vụ của shop</p>";
            try {
                JavaMailUtil.sendMail(accountModel.getEmail(),"Đăng ký thành công!",content);
            } catch (Exception e) {
                e.printStackTrace();
            }
            AccountRegister.reset();
            mapper.writeValue(resp.getOutputStream(),accountModel);

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session =req.getSession();
        String username =(String) session.getAttribute("loginName");
        if (username == null) {
            RequestDispatcher rd =req.getRequestDispatcher("views/web/index.jsp");
            rd.forward(req,resp);
        } else {
            AccountModel accountModel = accountService.findByUsername(username);
            AccountModel oldAccount = HttpUtil.of(req.getReader()).toModel(AccountModel.class);

            if (accountModel.getPassword().equals(oldAccount.getOldPassword()) && oldAccount.getPassword().equals(oldAccount.getConfirmation_pwd())) {
                accountModel.setPassword(oldAccount.getPassword());
                AccountModel newAccount= accountService.update(accountModel);
                mapper.writeValue(resp.getOutputStream(),accountModel);
            }
            else {
                return;
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        HttpSession session =req.getSession();
        String username =(String) session.getAttribute("loginName");
        AccountModel accountModel = accountService.findByUsername(username);//Cũ
        AccountModel oldAccount = accountModel;

        String userName = req.getParameter("userName");
        String email =req.getParameter("email");
        String address = req.getParameter("address");
        String phonenumber = req.getParameter("phoneNumber");

        oldAccount.setUsername(userName);
        oldAccount.setEmail(email);
        oldAccount.setAddress(address);
        oldAccount.setPhonenumber(phonenumber);

        boolean check = false;
        Message.reset();

        if(oldAccount.getUsername().equals(""))
        {

            Message.errUser="Vui long nhap UserName !";
            check = true;
        }
        if(oldAccount.getEmail().equals(""))
        {
            Message.errEmail = "Vui long nhap Email !";
            check = true;
        }
        if(oldAccount.getPhonenumber().equals(""))
        {
            Message.errPhone = "Vui long nhap SDT";
            check = true;
        }

        if(accountModel.getAddress().equals(""))
        {
            Message.errAddress = "Vui long nhap dia chi!";
            check = true;
        }
        if(check == true)
        {
            req.setAttribute("errUserName",Message.errUser);
            req.setAttribute("errEmail",Message.errEmail);
            req.setAttribute("errPhone",Message.errPhone);
            req.setAttribute("errAddress",Message.errAddress);
            req.setAttribute("accountModel",oldAccount);

            RequestDispatcher rd = req.getRequestDispatcher("/views/web/checkout.jsp");
            rd.forward(req,resp);
        }
        else
        {
            AccountModel newAccount= accountService.update(oldAccount);
//            mapper.writeValue(resp.getOutputStream(),oldAccount);
            req.setAttribute("accountModel",newAccount);
            RequestDispatcher rd = req.getRequestDispatcher("/views/web/checkout.jsp");
            rd.forward(req,resp);

        }
    }
}
