package com.anp.entities;

import java.time.LocalDate;

public class Library {
	
	private int book_id;
	private String book_name;
	private LocalDate assured_date;
	private LocalDate renewal_date;
	private LocalDate return_date;
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public LocalDate getAssured_date() {
		return assured_date;
	}
	public void setAssured_date(LocalDate assured_date) {
		this.assured_date = assured_date;
	}
	public LocalDate getRenewal_date() {
		return renewal_date;
	}
	public void setRenewal_date(LocalDate renewal_date) {
		this.renewal_date = renewal_date;
	}
	public LocalDate getReturn_date() {
		return return_date;
	}
	public void setReturn_date(LocalDate return_date) {
		this.return_date = return_date;
	}
	public Library(int book_id, String book_name, LocalDate assured_date, LocalDate renewal_date,
			LocalDate return_date) {
		super();
		this.book_id = book_id;
		this.book_name = book_name;
		this.assured_date = assured_date;
		this.renewal_date = renewal_date;
		this.return_date = return_date;
	}
	
	
	
}