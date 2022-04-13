<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:if test="${ !empty  sessionScope.message }">
	<div class="alert alert-success">${ sessionScope.message }</div>
	<c:remove var="message" scope="session" />
</c:if>

<c:if test="${ !empty  sessionScope.erro }">
	<div class="alert alert-danger">${ sessionScope.erro }</div>
	<c:remove var="erro" scope="session" />
</c:if>

<c:if test="${ empty  lstCartdt }">
	<div class="alert alert-danger text-center mt-3">
		<h4 class="text-danger">Chưa có lịch sử!</h4>
	</div>
</c:if>
<c:if test="${ !empty  lstCartdt }">
	<div class="table-responsive">
		<table class="table">
			<tr>
				<th>Hình ảnh</th>
				<th>Tên sản phẩm</th>
				<th>Số lượng</th>
				<th>Màu sắc</th>
				<th>Đơn giá</th>
				<th>Kích thước</th>
				<!-- 			<th>Tổng tiền</th> -->
			</tr>
			<c:forEach var="lstCartdt" items="${ lstCartdt }">
				<tr>
					<td><img alt="Product" style="max-height: 100px"
						src="/HiennvPH13697_SOF3011_Assignment/images/${ lstCartdt.product.img }"></td>
					<td>${ lstCartdt.product.category.ten}${ khoangTrang }${ lstCartdt.product.ten }</td>
					<td>${ lstCartdt.soLuong }</td>
					<td>${ lstCartdt.product.mauSac }</td>
					<td>${ lstCartdt.product.donGia }</td>
					<td>${ lstCartdt.product.kichThuoc }</td>
					<%-- 				<td>${ lstCartdt.cart.tongTien }</td> --%>
				</tr>
			</c:forEach>
		</table>
		<p>Địa chỉ:</p>
		<p>Số điện thoại:</p>
		<div class="text-center">
			<a href="/HiennvPH13697_SOF3011_Assignment/dathang?id=${ idCart }"
				class="btn btn-outline-secondary">Huỷ đơn hàng</a>
		</div>
	</div>
</c:if>

