package com.app.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ratings {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable = false)
	private User user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "product_id",nullable = true)
	private Product product;
	
	@Column(name="rating")
	private double rating;
	
	private LocalDateTime createdAt;
	
	public Ratings() {}

	public Ratings(User user, Product product, double rating) {
		super();
		this.user = user;
		this.product = product;
		this.rating = rating;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
