package com.sellinglaptop.controller.web;

import com.sellinglaptop.model.AccountModel;
import com.sellinglaptop.model.CartItemModel;
import com.sellinglaptop.model.CartModel;
import com.sellinglaptop.model.CustomerModel;
import com.sellinglaptop.utils.JavaMailUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns= {"/paySuccess"})
public class PaySuccessController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        CartModel cartModel=(CartModel) session.getAttribute("order");
        AccountModel user = (AccountModel) session.getAttribute("user");
        CustomerModel customer = (CustomerModel) session.getAttribute("customer") ;
        AccountModel user_cus = new AccountModel();
        if (customer != null) {
            user_cus.setUsername(customer.getFullname());
            user_cus.setAddress(customer.getAddress());
            user_cus.setPhonenumber(customer.getPhonenumber());
            user_cus.setEmail(customer.getEmail());
        } else {
            user_cus=user;
        }

        req.setAttribute("cart",cartModel);
        req.setAttribute("user",user_cus);



        double totalPrice=0;
        StringBuilder content = new StringBuilder("<p>Xin chào "+ user_cus.getUsername() +"<p>");
        content.append("<p>Đơn hàng #"+cartModel.getId()+" của bạn đã được giao thành công.</p>");
        content.append("<p>THÔNG TIN ĐƠN HÀNG - DÀNH CHO NGƯỜI MUA<p>");
        content.append("<table><tr><th>Tên Sản Phẩm       </th><th>Số lượng         </th><th>Đơn giá       </th></tr>");
        for(CartItemModel item : cartModel.getItemModelList()) {
            content.append("<tr><td>"+item.getProduct().getProductName()+"</td><td>    "+item.getQuantity()+"</td><td>   "+item.getUnitPrice()+" VNĐ</td></tr>");
            totalPrice += item.getQuantity()*item.getUnitPrice();
        }
        content.append("</table><br>");
        if (session.getAttribute("isOnline") != null) {
            content.append("<p>Hình thức thanh toán: Thanh toán online</p><br>");
            req.setAttribute("payment","Thanh toán online");
        } else {
                content.append("<p>Hình thức thanh toán: Trả tiền khi nhận hàng</p><br>");
            req.setAttribute("payment","Trả tiền khi nhận hàng");
        }
        content.append("<p>Tổng Tiền : "+totalPrice+" VNĐ</p>");
        try {
            JavaMailUtil.sendMail(user_cus.getEmail(),"Đặt hàng thành công!",content.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("totalPrice",totalPrice);
        session.removeAttribute("isOnline");
        session.removeAttribute("order");

        RequestDispatcher rd = req.getRequestDispatcher("views/web/continue-order.jsp");
        rd.forward(req,resp);
    }
}
