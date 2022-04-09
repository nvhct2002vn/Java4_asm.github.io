<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:if test="${ !empty  sessionScope.erro }">
	<div class="alert alert-danger">${ sessionScope.erro }</div>
	<c:remove var="erro" scope="session" />
</c:if>

<form class="row" action="/HiennvPH13697_SOF3011_Assignment/users/store"
	method="post">
	<div class="col-md-5 col-12 m-auto">
		<div>
			<label>Họ và Tên</label> <input class="form-control  mb-3"
				type="text" name="hoTen">
		</div>
		<div>
			<label>Địa chỉ</label> <input class="form-control mb-3" type="text"
				name="diaChi">
		</div>
		<div>
			<label>Email</label> <input class="form-control mb-3" type="email"
				name="email">
		</div>
		<div>
			<label>Mật Khẩu</label> <input class="form-control mb-3"
				type="password" name="password">
		</div>
		<div>
			<label>Số điện thoại</label> <input class="form-control mb-3"
				type="text" name="sdt">
		</div>
		<div class="mt-2 row">
			<label class="col-4" for="gioi_tinh">Giới tính</label>
			<div class="col-10 row">
				<div class="col-4">
					<input type="radio" value="0" name="gioi_tinh" id="gt_nam" checked />
					<label for="gt_nam">Nam</label>
				</div>
				<div class="col-4">
					<input type="radio" value="1" name="gioi_tinh" id="gt_nu" /> <label
						for="gt_nu">Nữ</label>
				</div>
			</div>
			<div>
				<select class="form-select" aria-label="Default select example"
					name="phanQuyen">
					<option value="0">Khách hàng</option>
					<option value="1">Admin</option>
				</select>
			</div>
			<div class="text-center mt-3">
				<button class="btn btn-outline-success">Đăng ký</button>
			</div>
			<div class="text-center mt-3 back-home">
				<a class="" href="/HiennvPH13697_SOF3011_Assignment/Home"> Quay
					về trang chủ? </a>
			</div>
		</div>
	</div>
</form>