package com.app.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column
	private String password;
	
	@Column
	private String email;
	
	@Column
    private String contact;
	
	@Column
	private String role;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Address> address = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Ratings> ratings = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Review> reviews= new ArrayList<>();
	
	public User() {}

	public User(String firstName, String lastName, String password, String email, String contact, List<Address> address,
			List<Ratings> ratings, List<Review> reviews, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.contact = contact;
		this.address = address;
		this.ratings = ratings;
		this.reviews = reviews;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}

