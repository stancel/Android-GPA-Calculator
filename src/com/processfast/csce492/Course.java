package com.processfast.csce492;

public class Course {
	private int id;
	private String dept;
	private String number;
	private String teacher;
	private int hours;
	private int style;
	private GradingScale scale;
	
	public Course(){
		dept = "";
		number = "";
		teacher = "";
		hours = -1;
		style = -1;
		scale = new GradingScale();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}
	
	@Override
	public String toString(){
		return this.id + " " + this.dept+" "+this.number + " " + this.teacher + " " + this.style;
	}
	public GradingScale getScale() {
		return scale;
	}
	public void setScale(GradingScale scale) {
		this.scale = scale;
	}
}
