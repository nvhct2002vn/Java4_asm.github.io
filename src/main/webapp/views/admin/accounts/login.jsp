<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:if test="${ !empty  sessionScope.message }">
	<div class="alert alert-success">${ sessionScope.message }</div>
	<c:remove var="message" scope="session" />
</c:if>

<c:if test="${ !empty  sessionScope.erro }">
	<div class="alert alert-danger">${ sessionScope.erro }</div>
	<c:remove var="erro" scope="session" />
</c:if>

<div class="row">
	<form method="post" class="col-12 col-md-5 m-auto"
		action="/HiennvPH13697_SOF3011_Assignment/accounts/checklogin">
		<div>
			<label class="form-label">Email</label> <input
				class="form-control mb-3" type="email" name="email">
		</div>
		<div>
			<label class="form-label">Password</label> <input
				class="form-control mb-3" type="password" name="password">
		</div>
		<div class="text-center">
			<button class="btn btn-outline-secondary">Đăng nhập</button>
		</div>
		<div class="text-center mt-3 back-home">
			<a class=""
				href="/HiennvPH13697_SOF3011_Assignment/accounts/register">Đăng ký tài khoản?</a>
		</div>
	</form>
</div>