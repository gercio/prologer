package com.lovesoft.androger.core.storage;

public class PointDTO {
	private double x;
	private double y;
	
	public PointDTO() {
	}
	
	public PointDTO(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
}
