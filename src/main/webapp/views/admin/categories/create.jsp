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
<%-- <c:if test="${userLogin.phanQuyen == 1}"> --%>
<%-- </c:if> --%>
<c:if test="${ !empty  sessionScope.erro }">
	<div class="alert alert-danger">${ sessionScope.erro }</div>
	<c:remove var="erro" scope="session" />
</c:if>
<div class="row ">
	<div class="col-md-5 col-12 m-auto">
		<form method="POST"
			action="/HiennvPH13697_SOF3011_Assignment/categories/store">
			<div>
				<label class="form-label">Tên danh mục</label> <input
					class="form-control  mb-3" type="text" name="ten" />
			</div>
			<div>
				<label class="form-label ">Người tạo</label> <select
					class="form-select mb-3" name="user_id">
					<c:forEach items="${ dsUser }" var="user">
						<option ${ user.id == userLogin.id?"selected":"" }
							value="${ user.id }">${ user.hoTen }</option>
					</c:forEach>
				</select>
			</div>

			<button class="btn btn-warning">Thêm mới</button>
		</form>
	</div>
</div>
