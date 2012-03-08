package com.processfast.csce492;

import android.os.Parcel;
import android.os.Parcelable;

public class Course implements Parcelable{
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
	public Course(Parcel source) {
		id = source.readInt();
		dept = source.readString();
		number = source.readString();
		teacher = source.readString();
		hours = source.readInt();
		style = source.readInt();
		scale = source.readParcelable(null);
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
		return this.dept+" "+this.number + " \t " + getCourseAverageString() + " " + getCourseGrade();
	}
	public GradingScale getScale() {
		return scale;
	}
	public void setScale(GradingScale scale) {
		this.scale = scale;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(id);
		dest.writeString(dept);
		dest.writeString(number);
		dest.writeString(teacher);
		dest.writeInt(hours);
		dest.writeInt(style);
		dest.writeParcelable(scale, 0);
	}
	public static final Parcelable.Creator<Course> CREATOR = new Parcelable.Creator<Course>() {

		@Override
		public Course createFromParcel(Parcel source) {
			return new Course(source);
		}

		@Override
		public Course[] newArray(int size) {
			return new Course[size];
		}

	};
	
	public String getCourseAverageString(){
		// TODO Algorithms
		return "Grade: N/A";
		
	}
	
	public String getCourseGrade(){
		// TODO algorithms
		return "";
	}
	public String getCourseTitle(){
		
		return this.getDept() + " " + this.getNumber();
	}
}
