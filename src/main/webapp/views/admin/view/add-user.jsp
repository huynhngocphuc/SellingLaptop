<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<c:url value="/views/admin/static" var="url"></c:url>
<c:url value="/api-admin-user" var="APIurl"></c:url>
<c:url value="/admin-user-list" var="ACurl"></c:url>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Edit User</title>
<!-- BOOTSTRAP STYLES-->
<link href="${url}/css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link href="${url}/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link href="${url}/css/custom.css" rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
</head>
<body>
	<div id="wrapper">
		<jsp:include page="/views/admin/view/nav-bar.jsp"></jsp:include>
		<!-- /. NAV TOP  -->
		<jsp:include page="/views/admin/view/slide-bar.jsp"></jsp:include>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<h2>Thêm người dùng</h2>
					</div>
				</div>
				<!-- /. ROW  -->
				<hr />
				<div class="row">
					<div class="col-md-12">
						<!-- Form Elements -->
						<div class="panel panel-default">
							<div class="panel-heading"></div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-6">
										<h3>Thông tin chi tiết người dùng</h3>
										
										<form role="form" <%--action="api-admin-user"--%>  method="post" enctype="multipart/form-data">
											<div class="form-group">
												<label>Tên người dùng</label> <input class="form-control"
													placeholder="Nhập tên người dùng" name="username" id="username" />
											</div>

											<div class="form-group">
												<label>Mật khẩu</label> <input class="form-control"
													placeholder="Nhập mật khẩu" type="password"
													name="password" id="password" />
											</div>
											<div class="form-group">
												<label>Email</label> <input class="form-control"
													placeholder="Nhập email" name="email" id="email" />
											</div>
											<div class="form-group">
												<label>Số điện thoại</label> <input class="form-control"
																			 placeholder="Nhập số điện thoại" name="phonenumber" id="phonenumber" />
											</div>
											<div class="form-group">
												<label>Địa chỉ</label> <input class="form-control"
																			 placeholder="Nhập mật khẩu" name="address" id="address" />
											</div>
											<div class="form-group">
												<label>Chức vụ</label>
												<div class="checkbox">
													<label> <input type="radio" value="1" name="role" class="roleid"/>Admin
													</label>
													<br>
													<label> <input type="radio" value="0" name="role" class="roleid" checked/>Client
													</label>
												</div>
											</div>
											<div class="form-group">
												<label>Avatar</label> <input type="file" name="avatar" id="avatar" />
											</div>
											<button type="submit" class="btn btn-default" id="btnAdd">Thêm</button>
											<button type="reset" class="btn btn-primary">Reset</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->
	</div>
	<!-- /. WRAPPER  -->
	<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
	<!-- JQUERY SCRIPTS -->
	<script src="${url}/js/jquery-1.10.2.js"></script>
	<!-- BOOTSTRAP SCRIPTS -->
	<script src="${url}/js/bootstrap.min.js"></script>
	<!-- METISMENU SCRIPTS -->
	<script src="${url}/js/jquery.metisMenu.js"></script>
	<!-- CUSTOM SCRIPTS -->
	<script src="${url}/js/custom.js"></script>

	<%--	Addition--%>
	<script>
		$('#btnAdd').click(function (e){
			e.preventDefault();
			var username= $('#username').val();
			var password= $('#password').val();
			var email= $('#email').val();
			var phonenumber=$('#phonenumber').val();
			var address=$('#address').val();
			var avatar= $('#avatar').val();
			if ($('.roleid').is(":checked"))
			{
				var roleId=parseInt($('.role').val());
			}


			//console.log(roleId);
			console.log(username);
			var data={
				"username":username,
				"password":password,
				"email":email,
				//"id":id,
				"avatar":avatar,
				"roleId":roleId,
				"phonenumber":phonenumber,
				"address":address

			}
			updateProduct(data)

		});
		function updateProduct(data){
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
					window.location.href = "${ACurl}?type=list";
				},
				errMode: function (error){
					console.log("Error");
				}
			})
		}
	</script>




</body>
</html>