package com.thingtrack.com.vaadin.addons.slidedata;

import java.sql.Date;

public class Invoice {
	private int invoiceId;
	private int offerId;
	private String code;
	private Date dateInvoice;
	private float price;
	
	public Invoice() {
		
	}
	public Invoice(int invoiceId, int offerId, String code, Date dateInvoice, float price) {
		this.setInvoiceId(invoiceId);
		this.setOfferId(offerId);
		this.setCode(code);
		this.setDateInvoice(dateInvoice);
		this.setPrice(price);
		
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setDateInvoice(Date dateInvoice) {
		this.dateInvoice = dateInvoice;
	}
	public Date getDateInvoice() {
		return dateInvoice;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getPrice() {
		return price;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public int getOfferId() {
		return offerId;
	}
}
