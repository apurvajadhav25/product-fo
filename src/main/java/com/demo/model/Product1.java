package com.demo.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Product1 {
	@Id
	int id;
	String name;
	String description;
	long price;
	String gender;
	int rating;
	String imageUrl;
	/*
	 * int filter1_id; int filter2_id; int filter3_id; int filter4_id; int
	 * filter5_id; int filter6_id;
	 */
	String slider_1;
	
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
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/*
	 * public int getFilter1_id() { return filter1_id; } public void
	 * setFilter1_id(int filter1_id) { this.filter1_id = filter1_id; } public int
	 * getFilter2_id() { return filter2_id; } public void setFilter2_id(int
	 * filter2_id) { this.filter2_id = filter2_id; } public int getFilter3_id() {
	 * return filter3_id; } public void setFilter3_id(int filter3_id) {
	 * this.filter3_id = filter3_id; } public int getFilter4_id() { return
	 * filter4_id; } public void setFilter4_id(int filter4_id) { this.filter4_id =
	 * filter4_id; } public int getFilter5_id() { return filter5_id; } public void
	 * setFilter5_id(int filter5_id) { this.filter5_id = filter5_id; } public int
	 * getFilter6_id() { return filter6_id; } public void setFilter6_id(int
	 * filter6_id) { this.filter6_id = filter6_id; }
	 */
	public String getSlider_1() {
		return slider_1;
	}
	public void setSlider_1(String slider_1) {
		this.slider_1 = slider_1;
	}
	public String getSlider_2() {
		return slider_2;
	}
	public void setSlider_2(String slider_2) {
		this.slider_2 = slider_2;
	}
	String slider_2;
	
	//@OneToMany(mappedBy = "product")
	//Set<Image> images;
	
	@ManyToOne()
	Filter1 filter1;
	
	

	
}
