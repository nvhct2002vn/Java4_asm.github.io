package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the carts database table.
 * 
 */
@Entity
@Table(name="carts")
@NamedQuery(name="Cart.findAll", query="SELECT c FROM Cart c")
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="ngay_mua")
	private Date ngayMua;

	@Column(name="tong_tien")
	private int tongTien;

	//bi-directional many-to-one association to Cartdetail
	@OneToMany(mappedBy="cart")
	private List<Cartdetail> cartdetails;

	public Cart() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getNgayMua() {
		return this.ngayMua;
	}

	public void setNgayMua(Date ngayMua) {
		this.ngayMua = ngayMua;
	}

	public int getTongTien() {
		return this.tongTien;
	}

	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}

	public List<Cartdetail> getCartdetails() {
		return this.cartdetails;
	}

	public void setCartdetails(List<Cartdetail> cartdetails) {
		this.cartdetails = cartdetails;
	}

	public Cartdetail addCartdetail(Cartdetail cartdetail) {
		getCartdetails().add(cartdetail);
		cartdetail.setCart(this);

		return cartdetail;
	}

	public Cartdetail removeCartdetail(Cartdetail cartdetail) {
		getCartdetails().remove(cartdetail);
		cartdetail.setCart(null);

		return cartdetail;
	}

}