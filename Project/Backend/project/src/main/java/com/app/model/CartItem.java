package com.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnore
	@ManyToOne
	private Cart cart;
	
	@ManyToOne
	private Product product;
	
	private int quantity;
	
	private Integer price;
	
	private Integer discountPrice;
	
	private int UserId;
	
	public CartItem() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}
	
	
}
