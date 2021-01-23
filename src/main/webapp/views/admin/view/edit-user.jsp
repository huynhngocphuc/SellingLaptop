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
						<h2>Chỉnh sửa người dùng</h2>
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
										<h3>Thông tin người dùng</h3>
										<%--<c:url value="/admin/user/edit" var="edit"></c:url>--%>
										<form role="form" <%--action="${edit }"--%>
											enctype="multipart/form-data">
											<input name="id" value="${user.id }" id="id" type="text" hidden="">
											<div class="form-group">
												<label>Tên người dùng</label> <input class="form-control"
													value="${user.username }" name="username" id="username"/>
											</div>
											<div class="form-group">
												<label>Mật khẩu</label> <input class="form-control"
													value="${user.password }" type="password" name="password" id="password"/>
											</div>
											<div class="form-group">
												<label>Email</label> <input class="form-control"
													value="${user.email }" name="email" id="email"/>
											</div>
											<div class="form-group">
												<label>Số điện thoại</label> <input class="form-control" value="${user.phonenumber}"
																					placeholder="Please enter phone number" name="phonenumber" id="phonenumber" />
											</div>
											<div class="form-group">
												<label>Địa chỉ</label> <input class="form-control" value="${user.address}"
																			  placeholder="Please enter address" name="address" id="address" />
											</div>
											<div class="form-group">
												<label>Chức vụ</label>
												<div class="checkbox">
													<label> <input type="radio" value="1" name="role" class="roleid"/>Admin
													</label> <br> <label> <input type="radio" value="0"
														name="role" class="roleid" checked="checked"/>Client
													</label>
												</div>

											</div>

											<div class="form-group">
												<label>Chọn Avatar</label> <input type="file"
													name="avatar" />
											</div>
											<button type="button" class="btn btn-default" id="btnEdit">Cập nhật</button>
											<button type="reset" class="btn btn-primary">Reset</button>
										</form>
									</div>
								</div>
							</div>
						</div>
						<!-- End Form Elements -->
					</div>
				</div>
				<!-- /. ROW  -->
				<div class="row">
					<div class="col-md-12"></div>
				</div>
				<!-- /. ROW  -->
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
		$('#btnEdit').click(function (e){
			e.preventDefault();
			var id =parseInt($('#id').val());
			var username= $('#username').val();
			var password= $('#password').val();
			var email= $('#email').val();
			var phonenumber=$('#phonenumber').val();
			var address=$('#address').val();
			var avatar= $('#avatar').val();
			if ($('.roleid').is(":checked"))
			{
				var roleId=parseInt($('.roleid').val());
			}


			console.log(roleId);
			console.log(username);
			var data={
				"id":id,
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
				type: 'PUT',
				enctype: 'multipart/form-data',
				processData:false,
				contentType: 'application/json',
				data:JSON.stringify(data),
				dataType: 'json',
				success: function (result){
					console.log("Success");
					window.location.href = "${ACurl}?type=list&id=${data.id}";
				},
				errMode: function (error){
					console.log("Error");
				}
			})
		}
	</script>

</body>
</html>