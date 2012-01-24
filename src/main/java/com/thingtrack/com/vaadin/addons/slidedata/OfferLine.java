package com.thingtrack.com.vaadin.addons.slidedata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class OfferLine implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8730745380972313964L;
	
	private int offerLineId;
	private int offerId;
	private int line;
	private String service;
	private float price;
	private Collection<Product> lines;
	
	public OfferLine () {
		
	}
	public OfferLine (int offerLineId, int offerId, int line, String service, float price) {
		this.offerLineId = offerLineId;
		this.offerId = offerId;
		this.line = line;
		this.service = service;
		this.price = price;
		
		this.lines = new ArrayList<Product>();
	}
	
	public void setOfferLineId(int offerLineId) {
		this.offerLineId = offerLineId;
	}
	public int getOfferLineId() {
		return offerLineId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public int getOfferId() {
		return offerId;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public int getLine() {
		return line;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getService() {
		return service;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getPrice() {
		return price;
	}
	public void setLines(Collection<Product> lines) {
		this.lines = lines;
	}
	public Collection<Product> getLines() {
		return lines;
	}

}
