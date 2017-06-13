package org.mohan.javabrains.messenger.database;

public class BeanFilter {

	private int year;
	private int start;
	private int size;
	
	
	public BeanFilter() {
		
	}
	
	public BeanFilter(int year, int start, int size) {
		this.year = year;
		this.start = start;
		this.size = size;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
}
