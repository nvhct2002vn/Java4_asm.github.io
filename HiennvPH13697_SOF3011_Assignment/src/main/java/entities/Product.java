package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="don_gia")
	private float donGia;

	private String img;

	@Column(name="kich_thuoc")
	private String kichThuoc;

	@Column(name="mau_sac")
	private String mauSac;

	@Column(name="so_luong")
	private int soLuong;

	private String ten;

	//bi-directional many-to-one association to Cartdetail
	@OneToMany(mappedBy="product")
	private List<Cartdetail> cartdetails;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getDonGia() {
		return this.donGia;
	}

	public void setDonGia(float donGia) {
		this.donGia = donGia;
	}

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getKichThuoc() {
		return this.kichThuoc;
	}

	public void setKichThuoc(String kichThuoc) {
		this.kichThuoc = kichThuoc;
	}

	public String getMauSac() {
		return this.mauSac;
	}

	public void setMauSac(String mauSac) {
		this.mauSac = mauSac;
	}

	public int getSoLuong() {
		return this.soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getTen() {
		return this.ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public List<Cartdetail> getCartdetails() {
		return this.cartdetails;
	}

	public void setCartdetails(List<Cartdetail> cartdetails) {
		this.cartdetails = cartdetails;
	}

	public Cartdetail addCartdetail(Cartdetail cartdetail) {
		getCartdetails().add(cartdetail);
		cartdetail.setProduct(this);

		return cartdetail;
	}

	public Cartdetail removeCartdetail(Cartdetail cartdetail) {
		getCartdetails().remove(cartdetail);
		cartdetail.setProduct(null);

		return cartdetail;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}