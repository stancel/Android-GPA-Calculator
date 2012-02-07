package com.processfast.csce492;

public class Course {
	private int id;
	private String dept;
	private String number;
	private String teacher;
	private int hours;
	private int style;
	private int a;
	private int a_minus;
	private int b_plus;
	private int b;
	private int b_minus;
	private int c_plus;
	private int c;
	private int c_minus;
	private int d_plus;
	private int d;
	private int d_minus;
	
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
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getA_minus() {
		return a_minus;
	}
	public void setA_minus(int a_minus) {
		this.a_minus = a_minus;
	}
	public int getB_plus() {
		return b_plus;
	}
	public void setB_plus(int b_plus) {
		this.b_plus = b_plus;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public int getB_minus() {
		return b_minus;
	}
	public void setB_minus(int b_minus) {
		this.b_minus = b_minus;
	}
	public int getC_plus() {
		return c_plus;
	}
	public void setC_plus(int c_plus) {
		this.c_plus = c_plus;
	}
	public int getC() {
		return c;
	}
	public void setC(int c) {
		this.c = c;
	}
	public int getC_minus() {
		return c_minus;
	}
	public void setC_minus(int c_minus) {
		this.c_minus = c_minus;
	}
	public int getD_plus() {
		return d_plus;
	}
	public void setD_plus(int d_plus) {
		this.d_plus = d_plus;
	}
	public int getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}
	public int getD_minus() {
		return d_minus;
	}
	public void setD_minus(int d_minus) {
		this.d_minus = d_minus;
	}
	@Override
	public String toString(){
		return this.id + " " + this.dept+" "+this.number;
	}
}
