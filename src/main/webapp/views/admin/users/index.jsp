<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
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
			href="/HiennvPH13697_SOF3011_Assignment/users/create">Thêm mới</a>
	</div>
	<table class="table">
		<tr>
			<th>Họ và Tên</th>
			<th>Giới tính</th>
			<th>Địa chỉ</th>
			<th>Email</th>
			<th>Sdt</th>
			<th>Phân quyền</th>
			<th colspan="2">Thao tác</th>
		</tr>
		<c:forEach var="user" items="${ ds }">
			<tr>
				<td>${ user.hoTen }</td>
				<td><c:choose>
						<c:when test="${ user.gioiTinh == 0}">Nam</c:when>
						<c:when test="${ user.gioiTinh == 1}">Nữ</c:when>
						<c:otherwise> - </c:otherwise>
					</c:choose></td>
				<td>${ user.diaChi }</td>
				<td>${ user.email }</td>
				<td>${ user.sdt }</td>
				<td>${ user.phanQuyen }</td>
				<td><a class="btn btn-info"
					href="/HiennvPH13697_SOF3011_Assignment/users/edit?id=${ user.id }">Cập
						nhật</a></td>
				<td>
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-danger" data-bs-toggle="modal"
						data-bs-target="#exampleModal${ user.id }">Xóa</button> <!-- Modal -->
					<div class="modal fade" id="exampleModal${ user.id }" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Xóa người
										dùng</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">Bạn chắc chắn muốn xóa người dùng
									này hay không ?</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Close</button>
									<a class="btn btn-danger"
										href="/HiennvPH13697_SOF3011_Assignment/users/delete?id=${ user.id }">Xóa</a>
								</div>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>


