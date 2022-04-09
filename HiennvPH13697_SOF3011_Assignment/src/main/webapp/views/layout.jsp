<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon"
	href="/HiennvPH13697_SOF3011_Assignment/images/logo.png" />
<link rel="stylesheet"
	href="/HiennvPH13697_SOF3011_Assignment/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="/HiennvPH13697_SOF3011_Assignment/codeCss/style.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css" />
</head>
<body>
	<div class="container">
		<header class="my-3 text-center">
			<a href="/HiennvPH13697_SOF3011_Assignment/home"><img
				src="/HiennvPH13697_SOF3011_Assignment/images/logo.png"
				style="width: 64px; height: 64px" class="my-3" alt="" /></a>
			<p class="fw-bold">Bài Assignment của Nguyễn Viết Hiên</p>
		</header>

		<nav
			class="navbar navbar-expand-lg navbar-light bg-light sticky-top fw-bold">
			<div class="container-fluid">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="/HiennvPH13697_SOF3011_Assignment/home">Trang chủ</a></li>
				</ul>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
					aria-controls="navbarNavDropdown" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse row px-3"
					id="navbarNavDropdown">
					<ul class="navbar-nav col-12 col-md-12 col-lg-10">
						<li class="nav-item"><a class="nav-link" href="#">Giới
								thiệu</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/HiennvPH13697_SOF3011_Assignment/list-products">Sản
								phẩm</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Liên hệ</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Hỏi đáp</a></li>
					</ul>
					<ul
						class="navbar-nav col-12 col-md-12 col-lg-2 justify-content-end p-0">
						<li class="nav-item"><a class="nav-link" href="/HiennvPH13697_SOF3011_Assignment/cart"> <i
								class="bi bi-bag"></i>
						</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownMenuLink" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> <i
								class="bi bi-person-circle"></i> <c:if
									test="${ !empty userLogin}">
									${ userLogin.hoTen }
								</c:if> <c:if test="${ empty userLogin}">
									Tài khoản
								</c:if>

						</a>
							<ul class="dropdown-menu"
								aria-labelledby="navbarDropdownMenuLink">
								<c:if test="${ empty userLogin}">
									<li><a
										href="/HiennvPH13697_SOF3011_Assignment/accounts/login"
										class="dropdown-item">Đăng nhập</a></li>
									<li><a
										href="/HiennvPH13697_SOF3011_Assignment/accounts/register"
										class="dropdown-item">Đăng ký</a></li>
								</c:if>
								<c:if test="${ !empty userLogin}">
									<c:if test="${ userLogin.phanQuyen == 1 }">
										<li><a
											href="/HiennvPH13697_SOF3011_Assignment/users/index"
											class="dropdown-item">Quản lý người dùng</a></li>
										<li><a
											href="/HiennvPH13697_SOF3011_Assignment/products/index"
											class="dropdown-item">Quản lý sản phẩm</a></li>
										<li><a
											href="/HiennvPH13697_SOF3011_Assignment/categories/index"
											class="dropdown-item">Quản lý loại sản phẩm</a></li>
										<li><a
											href="/HiennvPH13697_SOF3011_Assignment/carts/index"
											class="dropdown-item">Quản lý đơn hàng</a></li>
									</c:if>
									<li><a href="#" class="dropdown-item"
										data-bs-toggle="modal" data-bs-target="#doiMatKhau">Đổi
											mật khẩu</a></li>
									<li><a class="dropdown-item" href="#">Cập nhật tài
											khoản</a></li>
									<li><a class="dropdown-item"
										href="/HiennvPH13697_SOF3011_Assignment/accounts/logout">Đăng
											xuất</a></li>
								</c:if>
							</ul></li>
					</ul>
				</div>
			</div>
		</nav>

		<!-- 		<div class="modal fade" id="dangNhap" tabindex="-1" -->
		<!-- 			aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
		<!-- 			<div class="modal-dialog"> -->
		<!-- 				<div class="modal-content"> -->
		<!-- 					<div class="modal-header"> -->
		<!-- 						<h5 class="modal-title" id="exampleModalLabel">LOGIN</h5> -->
		<!-- 						<button type="button" class="btn-close" data-bs-dismiss="modal" -->
		<!-- 							aria-label="Close"></button> -->
		<!-- 					</div> -->
		<!-- 					<div class="modal-body"> -->
		<!-- 						<from action="" method=""> <lable class="form-lable">Tài -->
		<!-- 						khoản</lable> <input class="form-control" type="email" name=""> <lable -->
		<!-- 							class="form-lable">Mật khẩu</lable> <input class="form-control" -->
		<!-- 							type="password" name=""> -->
		<!-- 						<div class="row mb-3"> -->
		<!-- 							<div class="col-6"> -->
		<!-- 								<div class="form-check mt-3"> -->
		<!-- 									<input class="form-check-input" type="checkbox" value="" -->
		<!-- 										id="flexCheckDefault" /> <label class="form-check-labe" -->
		<!-- 										for="flexCheckDefault"> Remember Me! </label> -->
		<!-- 								</div> -->
		<!-- 							</div> -->
		<!-- 							<div class="col-6"> -->
		<!-- 								<a class="float-end mt-3" href="#" data-bs-toggle="modal" -->
		<!-- 									data-bs-target="#ForgotPassword">Forgot Password?</a> -->
		<!-- 							</div> -->
		<!-- 						</div> -->
		<!-- 						<div class="text-center"> -->
		<!-- 							<a href="#" class="btn btn-secondary mb-3"> Login Now</a> -->
		<!-- 						</div> -->
		<!-- 						<div class="text-center"> -->
		<!-- 							<span>Need an account?</span> <a href="/HiennvPH13697_SOF3011_Assignment/users/create"> Sign Up </a> -->
		<!-- 						</div> -->
		<!-- 						<hr /> -->
		<!-- 						<div class="text-center"> -->
		<!-- 							<button type="button" class="btn btn-outline-secondary"> -->
		<!-- 								<i class="bi bi-facebook"></i> Login with Facebook -->
		<!-- 							</button> -->
		<!-- 						</div> -->

		<!-- 						</from> -->
		<!-- 					</div> -->
		<!-- 				</div> -->
		<!-- 			</div> -->
		<!-- 		</div> -->


		<div>
			<jsp:include page="${ view }"></jsp:include>
		</div>
		<button type="button"
			class="btn btn-outline-secondary btn-floating btn-lg text-center"
			id="btn-back-to-top">
			<i class="bi bi-arrow-up"></i>
		</button>
		<footer class="text-center bg-secondary text-white p-3 mt-3">
			<p class="m-0">@2021 - design by Nguyễn Viết Hiên</p>
		</footer>
	</div>
	<script src="/HiennvPH13697_SOF3011_Assignment/codeJs/linkJs.js"></script>
	<script
		src="/HiennvPH13697_SOF3011_Assignment/js/sweetalert2.all.min.js"></script>
	<script src="/HiennvPH13697_SOF3011_Assignment/js/jquery.min.js"></script>
	<script src="/HiennvPH13697_SOF3011_Assignment/js/popper.min.js"></script>
	<script src="/HiennvPH13697_SOF3011_Assignment/js/bootstrap.min.js"></script>
</body>
</html>