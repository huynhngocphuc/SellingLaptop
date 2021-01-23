package com.sellinglaptop.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sellinglaptop.model.AccountModel;
import com.sellinglaptop.service.AccountService;
import com.sellinglaptop.utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-user"})
public class AccountAPI extends HttpServlet {
    @Inject
    private AccountService accountService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json"); //
        // Convert from type json to Model

        AccountModel accountModel = HttpUtil.of(req.getReader()).toModel(AccountModel.class);
        accountModel = accountService.save(accountModel);
        mapper.writeValue(resp.getOutputStream(),accountModel);
        System.out.println(accountModel);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json"); //
        // Convert from type json to Model
        AccountModel accountModel = HttpUtil.of(req.getReader()).toModel(AccountModel.class);
        accountModel = accountService.update(accountModel);
        mapper.writeValue(resp.getOutputStream(),accountModel);
        System.out.println(accountModel);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json"); //
        int id = Integer.parseInt(req.getReader().readLine());
        accountService.delete(id);
        mapper.writeValue(resp.getOutputStream(),null);
    }
}
