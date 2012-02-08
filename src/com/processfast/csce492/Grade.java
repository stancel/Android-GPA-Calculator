package com.processfast.csce492;

public class Grade {
	private int id;
	private int assignment_type_id;
	private int course_id;
	private float max_grade;
	private String name;
	private float grade;
	private String date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAssignment_type_id() {
		return assignment_type_id;
	}
	public void setAssignment_type_id(int assignment_type_id) {
		this.assignment_type_id = assignment_type_id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public float getMax_grade() {
		return max_grade;
	}
	public void setMax_grade(float max_grade) {
		this.max_grade = max_grade;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	@Override
	public String toString(){
		return this.name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
