
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="">
	<h4>Sản phẩm</h4>
</div>
<div class="row product_border">
	<c:forEach var="prd" items="${ dsPrd }">
		<div class="col-12 col-md-3">
			<div class="container_overlay title-img">
				<a href="/HiennvPH13697_SOF3011_Assignment/product?id=${ prd.id }">
					<img src="/HiennvPH13697_SOF3011_Assignment/images/${ prd.img }"
					alt="Avatar" class="image_product img-fluid" />
				</a>
				<!-- 					<div class="overlay"> -->
				<!-- 						<div class="img_hover"> -->
				<!-- 							<a href="#"> <img -->
				<!-- 								src="https://media.coolmate.me/uploads/January2022/nnjkids123_s1_672x990.jpg" -->
				<!-- 								alt="Avatar" class="image_product" /> -->
				<!-- 							</a> -->
				<!-- 						</div> -->
				<!-- 					</div> -->
			</div>
			<div class="text-center">
				<a class="product_name" href="#">${ prd.category.ten }${ khoangTrang }${ prd.ten }</a>
				<p class="product_price">${ prd.donGia }</p>
			</div>
		</div>
	</c:forEach>
</div>