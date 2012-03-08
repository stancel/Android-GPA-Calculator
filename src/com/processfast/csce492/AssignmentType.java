package com.processfast.csce492;

import android.os.Parcel;
import android.os.Parcelable;

public class AssignmentType implements Parcelable{
	private int id;
	private int course_id;
	private int weight;
	private String name;
	
	public AssignmentType(){
		name = "";
	}
	public AssignmentType(Parcel source) {
		id = source.readInt();
		course_id = source.readInt();
		weight = source.readInt();
		name = source.readString();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return "Type ID: " + this.id + " Course ID:" + this.course_id + " Name: " + this.name + " Weight:" + this.weight;
	}
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeInt(course_id);
		dest.writeInt(weight);
		dest.writeString(name);
	}
	
	public static final Parcelable.Creator<AssignmentType> CREATOR = new Parcelable.Creator<AssignmentType>() {

		@Override
		public AssignmentType createFromParcel(Parcel source) {
			return new AssignmentType(source);
		}

		@Override
		public AssignmentType[] newArray(int size) {
			return new AssignmentType[size];
		}

	};
	
	public String getAssignmentTypeAverage(){
		String average = "N/A";
		// TODO calculate average for this assignment type
		return this.getName() + "Average: " + average;
		
	}
}
