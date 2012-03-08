package com.processfast.csce492;

import android.os.Parcel;
import android.os.Parcelable;

public class Grade implements Parcelable{
	private int id;
	private int assignment_type_id;
	private int course_id;
	private float max_grade;
	private String name;
	private float grade;
	private String date;
	
	public Grade(){
		
	}
	
	public Grade(Parcel source) {
		id = source.readInt();
		assignment_type_id = source.readInt();
		course_id = source.readInt();
		max_grade = source.readFloat();
		name = source.readString();
		grade = source.readFloat();
		date = source.readString();
	}
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
		return "\t\t" + this.name + "\t" + getGrade() + "/" + getMax_grade();
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public int describeContents() {

		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeInt(assignment_type_id);
		dest.writeInt(course_id);
		dest.writeFloat(max_grade);
		dest.writeString(name);
		dest.writeFloat(grade);
		dest.writeString(date);
	}
	

	public static final Parcelable.Creator<Grade> CREATOR = new Parcelable.Creator<Grade>() {

		@Override
		public Grade createFromParcel(Parcel source) {
			return new Grade(source);
		}

		@Override
		public Grade[] newArray(int size) {
			return new Grade[size];
		}

	};
}
