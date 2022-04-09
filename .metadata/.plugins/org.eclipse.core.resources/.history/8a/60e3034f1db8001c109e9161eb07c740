package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cartdetails database table.
 * 
 */
@Entity
@Table(name="cartdetails")
@NamedQuery(name="Cartdetail.findAll", query="SELECT c FROM Cartdetail c")
public class Cartdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="don_gia")
	private int donGia;

	@Column(name="so_luong")
	private int soLuong;

	@Column(name="trang_thai")
	private int trangThai;

	//bi-directional many-to-one association to Cart
	@ManyToOne
	private Cart cart;

	//bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

	public Cartdetail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDonGia() {
		return this.donGia;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}

	public int getSoLuong() {
		return this.soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public int getTrangThai() {
		return this.trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public Cart getCart() {
		return this.cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}