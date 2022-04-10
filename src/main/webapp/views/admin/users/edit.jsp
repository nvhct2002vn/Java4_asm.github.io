<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<%-- <c:if test="${ empty userLogin}"> --%>
<!-- 	<div class="text-center"> -->
<!-- 		<h4>Vui lòng đăng nhập!</h4> -->
<!-- 	</div> -->
<%-- </c:if> --%>
<%-- <c:if test="${ !empty userLogin}"> --%>
<%-- 	<c:if test="${ userLogin.phanQuyen != 1 }"> --%>
<!-- 		<div class="text-center"> -->
<!-- 			<h4>Bạn không có quyền truy cập!</h4> -->
<!-- 		</div> -->
<%-- 	</c:if> --%>
<%-- </c:if> --%>
<%-- <c:if test="${userLogin.phanQuyen == 1}"> --%>
<%-- </c:if> --%>
	<c:if test="${ !empty  sessionScope.erro }">
		<div class="alert alert-danger">${ sessionScope.erro }</div>
		<c:remove var="erro" scope="session" />
	</c:if>

	<form class="row"
		action="/HiennvPH13697_SOF3011_Assignment/users/update?id=${ user.id }"
		method="post">
		<div class="col-md-5 col-12 m-auto">
			<div>
				<label>Họ và Tên</label> <input class="form-control  mb-3"
					type="text" name="hoTen" value="${ user.hoTen }">
			</div>
			<div>
				<label>Địa chỉ</label> <input class="form-control mb-3" type="text"
					name="diaChi" value="${ user.diaChi }">
			</div>
			<div>
				<label>Email</label> <input class="form-control mb-3" type="email"
					name="email" value="${ user.email }">
			</div>
			<!-- 		<div> -->
			<!-- 			<label>Mật Khẩu</label> <input class="form-control mb-3" -->
			<%-- 				type="password" name="password" value="${ user.password }"> --%>
			<!-- 		</div> -->
			<div>
				<label>Số điện thoại</label> <input class="form-control mb-3"
					type="text" name="sdt" value="${ user.sdt }">
			</div>
			<div>
				<label>Giới tính</label> <input type="radio" name="gioiTinh"
					${ user.gioiTinh == 0?"checked":"" } value="0" checked>Nam
				<input type="radio" name="gioiTinh"
					${ user.gioiTinh == 1?"checked":"" } value="1">Nữ
			</div>
			<div>
				<select class="form-select" aria-label="Default select example"
					name="phanQuyen">
					<option ${ user.phanQuyen == 0?"selected":"" } value="0">Khách
						hàng</option>
					<option ${ user.phanQuyen == 1?"selected":"" } value="1">Admin</option>
				</select>
			</div>
			<div class="text-center mt-3">
				<button class="btn btn-outline-success">Cập nhật</button>
			</div>
		</div>
	</form>