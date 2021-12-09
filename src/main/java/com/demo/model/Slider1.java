package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Slider1 {
	@Id
	long minValue;
	long maxValue;
	
	public long getMinValue() {
		return minValue;
	}
	public void setMinValue(long minValue) {
		this.minValue = minValue;
	}
	public long getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(long maxValue) {
		this.maxValue = maxValue;
	}
	

}
