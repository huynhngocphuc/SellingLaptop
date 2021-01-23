package com.sellinglaptop.controller.web;

import com.sellinglaptop.model.CartModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/client-order"})
public class OrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CartModel cartModel=(CartModel) session.getAttribute("order");
        if (cartModel!=null) {
            req.setAttribute("cartModel",cartModel);
            RequestDispatcher rd =req.getRequestDispatcher("views/web/done-order.jsp");
        }
    }
}
