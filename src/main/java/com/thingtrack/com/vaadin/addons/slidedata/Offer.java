package com.thingtrack.com.vaadin.addons.slidedata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Offer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4168321394651820121L;

	private int offerId;
	private String code;
	private String revision;
	private float totalPrice;
	private String comment;
	private Collection<OfferLine> lines;
	private Collection<Invoice> invoices;
	
	public Offer () {
		
	}
	
	public Offer (int offerId, String code, String revision, float totalPrice, String comment) {
		this.offerId = offerId;
		this.code = code;
		this.revision = revision;
		this.totalPrice = totalPrice;
		this.comment = comment;
				
		this.lines = new ArrayList<OfferLine>();
		this.invoices = new ArrayList<Invoice>();
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public String getRevision() {
		return revision;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public void setLines(Collection<OfferLine> lines) {
		this.lines = lines;
	}

	public Collection<OfferLine> getLines() {
		return lines;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setInvoices(Collection<Invoice> invoices) {
		this.invoices = invoices;
	}

	public Collection<Invoice> getInvoices() {
		return invoices;
	}
	
}
