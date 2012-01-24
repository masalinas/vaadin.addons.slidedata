package com.thingtrack.com.vaadin.addons.slidedata;

public class Product {
	private int productId;
	private int offerLineId;
	private String name;
	private String description;
	
	public Product() {
		
	}
	public Product(int productId, int offerLineId, String name, String description) {
		this.productId = productId;
		this.offerLineId = offerLineId;
		this.name = name;
		this.description = description;
		
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getProductId() {
		return productId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public void setOfferLineId(int offerLineId) {
		this.offerLineId = offerLineId;
	}
	public int getOfferLineId() {
		return offerLineId;
	}
	
}
