package com.app.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Product {
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", quantity=" + quantity
				+ ", price=" + price + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", brand=" + brand
				+ ", imageUrl=" + imageUrl + ", discountPrice=" + discountPrice + ", discountPercent=" + discountPercent
				+ ", ratings=" + ratings + ", reviews=" + reviews + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="price")
	private int price;
	
	@Column(name="min_price")
	private int minPrice;
	
	@Column(name="max_price")
	private int maxPrice;
	
	@Column(name="brand")
	private String brand;
	
	@Column(name="Url")
	private String imageUrl;
	
	@Column(name="discount_price")
	private int discountPrice;
	
	@Column(name="discount_percent")
	private int discountPercent;
	
	
	@OneToMany(mappedBy = "product")
	private List<Ratings> ratings = new ArrayList<>();

	@OneToMany(mappedBy = "product")
	private List<Review> reviews = new ArrayList<>();
	
	public Product() {}

	
	public Product(String name, String description, int quantity, int price, String brand, String imageUrl,
			List<Ratings> ratings, List<Review> reviews) {
		super();
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.brand = brand;
		this.imageUrl = imageUrl;
		this.ratings = ratings;
		this.reviews = reviews;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<Ratings> getRatings() {
		return ratings;
	}

	public void setRatings(List<Ratings> ratings) {
		this.ratings = ratings;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}


	public int getDiscountPrice() {
		return discountPrice;
	}


	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}


	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}
	
	
	public int getMinPrice() {
		return minPrice;
	}


	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

}
