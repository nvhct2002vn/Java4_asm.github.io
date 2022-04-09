<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%-- <c:if test="${ empty userLogin}"> --%>
<!-- 	<div class="text-center"> -->
<!-- 		<h4>Vui lòng đăng nhập!</h4> -->
<!-- 	</div> -->
<%-- </c:if> --%>
<%-- <c:if test="${ !empty userLogin}"> --%>
<%-- 	<c:if test="${ user.phanQuyen != 1 }"> --%>
<!-- 		<div class="text-center"> -->
<!-- 			<h4>Bạn không có quyền truy cập!</h4> -->
<!-- 		</div> -->
<%-- 	</c:if> --%>
<%-- </c:if> --%>
<%-- <c:if test="${userLogin.phanQuyen == 1}"> --%>

<%-- </c:if> --%>
<c:if test="${ !empty  sessionScope.message }">
	<div class="alert alert-success">${ sessionScope.message }</div>
	<c:remove var="message" scope="session" />
</c:if>

<c:if test="${ !empty  sessionScope.erro }">
	<div class="alert alert-danger">${ sessionScope.erro }</div>
	<c:remove var="erro" scope="session" />
</c:if>

<div class="table-responsive">
	<div class="text-center">
		<a class="btn btn-warning mt-2"
			href="/HiennvPH13697_SOF3011_Assignment/products/create">Thêm mới</a>
	</div>
	<table class="table">
		<tr>
			<th>Tên sản phẩm</th>
			<th>Số lượng</th>
			<th>Màu sắc</th>
			<th>Đơn giá</th>
			<th>Kích thước</th>
			<th>Hình ảnh</th>
			<!-- 			<th>Người thêm</th> -->
			<th colspan="2">Thao tác</th>
		</tr>
		<c:forEach var="Prd" items="${ dsPrd }">
			<tr>
				<td>${ Prd.category.ten}${ khoangTrang }${ Prd.ten }</td>
				<td>${ Prd.soLuong }</td>
				<td>${ Prd.mauSac }</td>
				<td>${ Prd.donGia }</td>
				<td>${ Prd.kichThuoc }</td>
				<td>${ Prd.img }</td>
<%-- 				<td>${ Prd.category.user.hoTen }</td> --%>
				<td><a class="btn btn-info"
					href="/HiennvPH13697_SOF3011_Assignment/products/edit?id=${ Prd.id }">Sửa</a>
				</td>
				<td>
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-danger" data-bs-toggle="modal"
						data-bs-target="#exampleModal${ Prd.id }">Xóa</button> <!-- Modal -->
					<div class="modal fade" id="exampleModal${ Prd.id }" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Xóa sản
										phẩm</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">Bạn chắc chắn muốn xóa sản phẩm
									này hay không ?</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Close</button>
									<a class="btn btn-danger"
										href="/HiennvPH13697_SOF3011_Assignment/products/delete?id=${ Prd.id }">Xóa</a>
								</div>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
