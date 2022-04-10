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
	<form class="row"
		action="/HiennvPH13697_SOF3011_Assignment/categories/update?id=${ cate.id }"
		method="post">
		<div class="col-md-5 col-12 m-auto">
			<div>
				<label>Loại sản phẩm</label> <input class="form-control  mb-3"
					type="text" name="ten" value="${ cate.ten }">
			</div>
			<div class="text-center mt-3">
				<button class="btn btn-outline-success">Sửa</button>
			</div>
		</div>
	</form>