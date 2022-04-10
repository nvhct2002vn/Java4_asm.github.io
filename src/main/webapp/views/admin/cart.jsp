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
	<table class="table">
		<tr>
			<th>Hình ảnh</th>
			<th>Tên sản phẩm</th>
			<th>Số lượng</th>
			<th>Màu sắc</th>
			<th>Đơn giá</th>
			<th>Kích thước</th>
			<th>Tổng tiền</th>
			<th colspan="1">Thao tác</th>
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
				<td>${ lstCartdt.cart.tongTien }</td>
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
										href="/HiennvPH13697_SOF3011_Assignment/products/delete?id=${ lstCartdt.id }">Xóa</a>
								</div>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>

	<div class="text-center">
		<a href="/HiennvPH13697_SOF3011_Assignment/products/delete" class="btn btn-outline-secondary">Đặt hàng</a>
	</div>

</div>