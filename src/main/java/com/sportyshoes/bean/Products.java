package com.sportyshoes.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table (name="Products")
@NamedQueries({
@NamedQuery(name="Product.getProducts",
    query="SELECT p FROM Products p")     
})
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	
	private String productName;
	
	private long productPrice;
	
	@ManyToOne
	private ProductCategory productCategory;
	
	protected Products() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Products(String productName, long productPrice, ProductCategory productCategory) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.productCategory = productCategory;
	}


	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ProductCategory getProduct() {
		return productCategory;
	}

	public void setProduct(ProductCategory productCategory) {
		this.productCategory =productCategory;
	}

	public long getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(long productPrice) {
		this.productPrice = productPrice;
	}



}
