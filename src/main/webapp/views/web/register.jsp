<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:url value="/api-user-dangky" var ="APIurl"></c:url>
<c:url value="/trang-chu" var="url"> </c:url>
<!DOCTYPE html>
<html>
<head>
    <title>Registation Form * Form Tutorial</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h2 class="text-center">Đăng ký tài khoản</h2>
        </div>

        <div class="panel-body">
            <div class="form-group">
                <label for="username">User Name:</label>
                <input required="true" type="text" class="form-control" id="username"
                       value="${accountRegister.username}" placeholder = "Nhập User" />
                <label style="color: red">${errUserName}</label>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input required="true" type="email" class="form-control" id="email"
                value="${accountRegister.email}" placeholder = "Nhập Email">
                <label style="color: red">${errEmail}</label>
                <label></label>
            </div>
            <div class="form-group">
                <label for="phone">Phone number:</label>
                <input required="true" type="phone" class="form-control" id="phone"
                       value="${accountRegister.phonenumber}"
                       placeholder = "Nhập số điện thoại">
                <label style="color: red">${errPhone}</label>
            </div>
            <div class="form-group">
                <label for="pwd">Password:</label>
                <input required="true" type="password" class="form-control" id="pwd"
                       value="${accountRegister.password}" placeholder = "Nhập password">
                <label style="color: red">${errPassword}</label>
            </div>
            <div class="form-group">
                <label for="confirmation_pwd">Confirmation Password:</label>
                <input required="true" type="password" class="form-control" id="confirmation_pwd" placeholder =
                        "Nhập lại mật khẩu">
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" class="form-control" id="address"
                       value="${accountRegister.address}"placeholder = "Nhập địa chỉ">
                <label style="color: red">${errAddress}</label>
            </div>
            <button type="button" class="btn btn-success" id="btnRegister">Register</button>
            <button class="btn btn-success" onclick="document.location='${pageContext.request.contextPath}/trang-chu?action=home'">Cancel</button>
        </div>
    </div>
</div>
<script>
    $('#btnRegister').click(function (e){
        e.preventDefault();
        var username= $('#username').val();
        var email= $('#email').val();
        var phone= $('#phone').val();
        var pwd= $('#pwd').val();
        var confirmation_pwd= $('#confirmation_pwd').val();
        var address= $('#address').val();
        var data={
            "username":username,
            "email":email,
            "phonenumber":phone,
            "password":pwd,
            "confirmation_pwd":confirmation_pwd,
            "avatar":'000.jpg',
            "address":address,
            "roleId": 0
        }
        registerAccount(data)
    });
    function registerAccount(data){
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            enctype: 'multipart/form-data',
            processData:false,
            contentType: 'application/json',
            data:JSON.stringify(data),
            dataType: 'json',

            success: function (result){
                console.log("Success");
                window.location.href = "${url}?action=dangnhap";
            },
            error: function (error){
                console.log("Error");

                window.location.href = "${url}?action=register";

            }

        })
    }
</script>
</body>
</html>
