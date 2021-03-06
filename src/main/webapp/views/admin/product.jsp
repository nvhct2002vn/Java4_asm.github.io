<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="row">
	<div class="col-12 col-md-7 mt-3 title-img">
		<img class="img-fluid" alt=""
			src="/HiennvPH13697_SOF3011_Assignment/images/${ prd.img }">
	</div>
	<div class="col-12 col-md-5 mt-3">
		<h4 class="fw-bold">${ prd.category.ten }${ khoangTrang }${ prd.ten }</h4>
		<p>Giá: ${ prd.donGia }</p>
		<p>Màu: ${ prd.mauSac }</p>
		<p>Kích thước: ${ prd.kichThuoc }</p>
		<p>Hiện có: ${ prd.soLuong }</p>
		<label class="form-lable">Số lượng:</label>
		<div class="row">
			<div class="col-3">
				<input class="form-control" type="number" min="1"
					max="${ prd.soLuong }" name="soLuongMua">
			</div>
			<div class="col-9">
				<a
					href="/HiennvPH13697_SOF3011_Assignment/addprdtocard?id=${ prd.id }"
					class="btn btn-outline-secondary">Thêm vào rỏ hàng</a>
			</div>
		</div>
	</div>
</div>