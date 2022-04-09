<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="trangChu">
	<div class="row mt-3">
		<article class="col-12 slide-show-img">
			<div id="carouselExampleControls" class="carousel slide"
				data-bs-ride="carousel" data-bs-interval="2000">
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img
							src="https://file.vfo.vn/hinh/2015/12/meo-con-dang-yeu-lam-hinh-nen-may-tinh-4.jpg"
							alt="..." />
					</div>
					<div class="carousel-item">
						<img
							src="https://file.vfo.vn/hinh/2015/12/meo-con-dang-yeu-lam-hinh-nen-may-tinh-5.jpg"
							alt="..." />
					</div>
					<div class="carousel-item">
						<img
							src="https://file.vfo.vn/hinh/2015/12/meo-con-dang-yeu-lam-hinh-nen-may-tinh-6.jpg"
							alt="..." />
					</div>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleControls" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleControls" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
		</article>
	</div>
	<hr />
	<div class="row mt-3">
		<div class="text-center col-12 mb-3">
			<h1 class="border border-2 border-secondary px-2 d-inline fw-bold">
				Sản phẩm</h1>
		</div>
	</div>
	<hr />
	<div class="row product_border">
		<c:forEach var="prd" items="${ dsPrd }">
			<div class="col-12 col-md-3">
				<div class="container_overlay title-img">
					<a href="/HiennvPH13697_SOF3011_Assignment/product?id=${ prd.id }"> 
					<img
						src="/HiennvPH13697_SOF3011_Assignment/images/${ prd.img }"
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
	<div class="text-center my-3">
		<button type="button" class="btn btn-outline-secondary">
			<a href="#" class="text-black text-decoration-none">Xem Tất Cả</a>
		</button>
	</div>
	<hr />
	<div class="row mt-3">
		<div class="title-text text-center mb-3">
			<h1 class="border border-2 border-secondary px-2 d-inline fw-bold">
				Môn học phổ biến.</h1>
		</div>
		<div class="col-md-3 col-12">
			<a href="#"> <img src="Images/java.jpg" alt="" class="img-fluid" />
			</a>
			<p class="text-center">
				<a class="text-black noi-bat" href="#">Lorem ipsum dolor sit,
					amet consectetur adipisicing elit.</a>
			</p>
		</div>
		<div class="col-md-3 col-12">
			<a href="#"> <img src="Images/html.jpg" alt="" class="img-fluid" />
			</a>
			<p class="text-center">
				<a class="text-black noi-bat" href="#">Lorem ipsum dolor sit,
					amet consectetur adipisicing elit.</a>
			</p>
		</div>
		<div class="col-md-3 col-12">
			<a href="#"> <img src="Images/javascript.jpg" alt=""
				class="img-fluid" />
			</a>
			<p class="text-center">
				<a class="text-black noi-bat" href="#">Lorem ipsum dolor sit,
					amet consectetur adipisicing elit.</a>
			</p>
		</div>
		<div class="col-md-3 col-12">
			<a href="#"> <img src="Images/angularjs.jpg" alt=""
				class="img-fluid" />
			</a>
			<p class="text-center">
				<a class="text-black noi-bat" href="#">Lorem ipsum dolor sit,
					amet consectetur adipisicing elit.</a>
			</p>
		</div>
	</div>
	<hr />
	<div class="row">
		<div class="col-12 anh-tuong-trung">
			<img src="Images/javascript.jpg" alt="" class="img-fluid w-100 my-5" />
		</div>
	</div>
	<hr />
	<div class="row mt-3">
		<div class="title-text text-center mb-3">
			<h1 class="border border-2 border-secondary px-2 d-inline fw-bold">
				Môn học khác.</h1>
		</div>
		<div class="col-md-3 col-12">
			<a href="#"> <img src="Images/java.jpg" alt="" class="img-fluid" />
			</a>
			<p class="text-center">
				<a class="text-black noi-bat" href="#">Lorem ipsum dolor sit,
					amet consectetur adipisicing elit.</a>
			</p>
		</div>
		<div class="col-md-3 col-12">
			<a href="#"> <img src="Images/html.jpg" alt="" class="img-fluid" />
			</a>
			<p class="text-center">
				<a class="text-black noi-bat" href="#">Lorem ipsum dolor sit,
					amet consectetur adipisicing elit.</a>
			</p>
		</div>
		<div class="col-md-3 col-12">
			<a href="#"> <img src="Images/javascript.jpg" alt=""
				class="img-fluid" />
			</a>
			<p class="text-center">
				<a class="text-black noi-bat" href="#">Lorem ipsum dolor sit,
					amet consectetur adipisicing elit.</a>
			</p>
		</div>
		<div class="col-md-3 col-12">
			<a href="#"> <img src="Images/angularjs.jpg" alt=""
				class="img-fluid" />
			</a>
			<p class="text-center">
				<a class="text-black noi-bat" href="#">Lorem ipsum dolor sit,
					amet consectetur adipisicing elit.</a>
			</p>
		</div>
	</div>
	<hr />
</div>
