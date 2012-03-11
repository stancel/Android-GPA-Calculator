package com.processfast.csce492;

import java.math.BigDecimal;
import java.util.List;

import android.content.Context;
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
	
	private AssignmentTypesSource typeSource;
	
	private Context ctx;
	
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
		return this.dept+" "+this.number + " \t " + getCourseCurrentGrade() + " " + getCourseGrade();
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
	
	public double getCourseAverageValue(){
		typeSource = new AssignmentTypesSource(getContext());
		typeSource.open();
		List<AssignmentType> types = typeSource.getAllAssignmentTypesForCourse(this.getId());
		typeSource.close();
		AssignmentType t;
		double usedWeightSum = 0;
		double weightedAvg;
		double factor = 0;
		double avg = 0;
		for( int i = 0 ; i < types.size(); ++i){
			t = types.get(i);
			double typeAvg = t.getAssignmentTypeAverageValue(getContext());
			if(typeAvg >= 0){
				usedWeightSum += t.getWeight();
				avg += t.getWeight() * typeAvg;		
			}
		}
		if(usedWeightSum > 0){
			factor = 100 / usedWeightSum;
			return avg * factor;
		}
		else return -1;
		
	}
	public String getCourseCurrentGrade(){
		// TODO Algorithms
		double avg = getCourseAverageValue();
		if(avg > -1){
			BigDecimal trimmedAvg = new BigDecimal(avg);
			trimmedAvg = trimmedAvg.setScale(2, BigDecimal.ROUND_UP);
			return "Grade: " + trimmedAvg + " % " + scale.getLetterGrade(avg);
		}
		else{
			return "Grade: N/A";
		}
		
		
	}
	
	public String getCourseGrade(){
		// TODO algorithms
		return "";
	}
	public String getCourseTitle(){
		
		return this.getDept() + " " + this.getNumber();
	}
	public Context getContext() {
		return ctx;
	}
	public void setContext(Context ctx) {
		this.ctx = ctx;
	}
}
