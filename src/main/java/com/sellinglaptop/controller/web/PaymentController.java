package com.sellinglaptop.controller.web;

import com.sellinglaptop.model.AccountModel;
import com.sellinglaptop.model.CartItemModel;
import com.sellinglaptop.model.CartModel;
import com.sellinglaptop.model.CustomerModel;
import com.sellinglaptop.service.AccountService;
import com.sellinglaptop.service.CustomerService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(urlPatterns= {"/payment"})
public class PaymentController extends HttpServlet {
    @Inject
    CustomerService customerService;
    @Inject
    AccountService accountService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        /*HttpSession httpSession = req.getSession();
        String u_email = (String) httpSession.getAttribute("email");
        CartEntity cartEntity = (CartEntity) httpSession.getAttribute("cartEntity");

        UserEntity userEntity = new UserEntity();
        userEntity = userService.getUser(u_email);
        int c_id = customerService.getCustomerId(userEntity.getId());

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFullname(fullname);
        customerEntity.setEmail(email);
        customerEntity.setPhone(phone);
        customerEntity.setAddress(address);

        if(c_id!=0) {
            customerEntity.setId(c_id);
            customerService.edit(customerEntity);
        }
        else {
            customerService.insert(customerEntity);
            int new_c_id = customerService.getNewIDCustomer();
            customerEntity.setId(new_c_id);

            cartService.UpdateCustomer(cartEntity.getId(),new_c_id);
        }

        cartEntity.setCustomerEntity(customerEntity);
        httpSession.setAttribute("cartEntity",cartEntity);*/

        HttpSession session = req.getSession();
        session.setAttribute("isOnline","True");
        CartModel cartModel =  (CartModel) session.getAttribute("order");

        String userName = (String) session.getAttribute("loginName");
        if (cartModel != null) {
            if (userName == null) {
                CustomerModel customerModel = new CustomerModel();
                customerModel.setFullname(fullname);
                customerModel.setEmail(email);
                customerModel.setPhonenumber(phone);
                customerModel.setAddress(address);
                session.setAttribute("customer", customerModel);
            }
            else {
                AccountModel accountModel = accountService.findByUsername(userName);
                session.setAttribute("user",accountModel);
            }
        }


        if(cartModel !=null){
            String chuoi="";
            chuoi+="upload=1";
            chuoi+="&&return=http://localhost:8080/Sellinglaptop_war_exploded/api-user-order";
            chuoi+="&&cmd=_cart";
            chuoi+="&&business=chuShop@gmail.com";

            int i=1;

            for(CartItemModel cartItemModel : cartModel.getItemModelList()){
                chuoi+=removeAccent("&&item_name_"+i+"="+cartItemModel.getProduct().getProductName());
                chuoi+="&&quantity_"+i+"="+cartItemModel.getQuantity();
                chuoi+="&&amount_"+i+"="+(cartItemModel.getUnitPrice()/24000);
                i++;
            }
            resp.sendRedirect("https://www.sandbox.paypal.com/cgi-bin/webscr?"+chuoi);
        }
        else
        {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/paySuccess");
            dispatcher.forward(req, resp);
        }
    }

    private static final char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
            'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â',
            'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý',
            'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
            'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
            'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
            'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
            'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
            'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
            'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
            'ữ', 'Ự', 'ự',};

    private static final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
            'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a',
            'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
            'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
            'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
            'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
            'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
            'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
            'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
            'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
            'U', 'u', 'U', 'u',};

    public static char removeAccent(char ch) {
        int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
        if (index >= 0) {
            ch = DESTINATION_CHARACTERS[index];
        }
        return ch;
    }

    public static String removeAccent(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }
}

