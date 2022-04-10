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

<div class="table-responsive">
	<div class="text-center">
		<a class="btn btn-warning mt-2"
			href="/HiennvPH13697_SOF3011_Assignment/products/create">Thêm mới</a>
	</div>

	<div>
		<label class="form-lable">Hoá đơn:</label> <select name="cart"
			class="form-select">
			<c:forEach var="lstCart" items="${ lstCart }">
				<option value="${ lstCart.id }">${ lstCart.id }</option>
			</c:forEach>
		</select>
	</div>

	<table class="table">
		<tr>
			<th>Id đơn hàng</th>
			<th>Số lượng</th>
			<th>Tổng tiền</th>
			<th>Ngày mua</th>
			<th>Trạng thái</th>
			<!-- 			<th>Người thêm</th> -->
			<th colspan="2">Thao tác</th>
		</tr>
		<c:forEach var="lstCart" items="${ lstCart }">
			<tr>
				<td>${ lstCart.id }</td>
				<td>${ lstCart.cartdetail.soLuong }</td>
				<td>${ lstCart.tongTien }</td>
				<td>${ lstCart.ngayMua }</td>
				<td>${ lstCart.cartdetail.trangThai == 0?"Chưa thanh toán":"Đã thanh toán" }</td>
				<td><a class="btn btn-info"
					href="/HiennvPH13697_SOF3011_Assignment/carts/edit?id=${ lstCartdt.id }">Xem</a></td>
				<td>
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-danger" data-bs-toggle="modal"
						data-bs-target="#exampleModal${ lstCartdt.id }">Xóa</button> <!-- Modal -->
					<div class="modal fade" id="exampleModal${ lstCartdt.id }"
						tabindex="-1" aria-labelledby="exampleModalLabel"
						aria-hidden="true">
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
										href="/HiennvPH13697_SOF3011_Assignment/carts/delete?id=${ lstCartdt.id }">Xóa</a>
								</div>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
