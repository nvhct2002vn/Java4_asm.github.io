<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<%-- <c:if test="${user.phanQuyen == 1}"> --%>
<%-- </c:if> --%>
	<c:if test="${ !empty  sessionScope.erro }">
		<div class="alert alert-danger">${ sessionScope.erro }</div>
		<c:remove var="erro" scope="session" />
	</c:if>
	<form class="row"
		action="/HiennvPH13697_SOF3011_Assignment/products/store"
		method="post" enctype="multipart/form-data">
		<div class="col-md-5 col-12 m-auto">
			<div>
				<label>Danh mục sản phẩm</label> <select class="form-select mb-3"
					name="category_id">
					<c:forEach var="Cate" items="${ dsCate }">
						<option value="${ Cate.id }">${ Cate.ten }</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<label>Tên sản phẩm</label> <input class="form-control  mb-3"
					type="text" name="ten">
			</div>
			<div>
				<label>Số lượng</label> <input class="form-control mb-3"
					type="number" name="soLuong">
			</div>
			<div>
				<label>Màu sắc</label> <input class="form-control mb-3" type="text"
					name="mauSac">
			</div>
			<div>
				<label>Đơn giá</label> <input class="form-control mb-3"
					type="number" name="donGia">
			</div>
			<div>
				<label>Kích thước</label> <input class="form-control mb-3"
					type="text" name="kichThuoc">
			</div>
			<div>
				<label>Hình ảnh</label> <input class="form-control mb-3" type="file"
					name=img>
			</div>
			<div class="text-center mt-3">
				<button class="btn btn-outline-success">Thêm sản phẩm</button>
			</div>
		</div>
	</form>