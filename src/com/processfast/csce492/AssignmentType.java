package com.processfast.csce492;

import java.math.BigDecimal;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

public class AssignmentType implements Parcelable {
	private int id;
	private int course_id;
	private int weight;
	private String name;
	private GradesDataSource gradeSource;

	public AssignmentType() {
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
	public String toString() {
		return this.name + " " + this.weight + " %";
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

	public double getAssignmentTypeAverageValue(Context cntxt) {
		gradeSource = new GradesDataSource(cntxt);
		gradeSource.open();

		List<Grade> grades = gradeSource.getAllGradesByAssignmentType(this
				.getId());
		double avg = 0;
		if(grades.size() == 0){
			avg = -1;
		}
		
		for (int i = 0; i < grades.size(); ++i) {
			avg += grades.get(i).getGrade() / grades.get(i).getMax_grade();
		}
		if(avg >= 0) avg /= grades.size();
		gradeSource.close();

		return avg;
	}

	public String getAssignmentTypeAverage(Context cntxt, Course c) {

		GradingScale scale = c.getScale();
		// TODO calculate average for this assignment type...if there isn't an
		// assignment type
		// don't calculate anything. TODO format average to 2 decimals
		double avg = getAssignmentTypeAverageValue(cntxt);

		if (avg == -1) {
			return this.getName() + " Average: N/A";
		} else {
			BigDecimal trimmedAvg = new BigDecimal(100 * avg);
			trimmedAvg = trimmedAvg.setScale(2, BigDecimal.ROUND_UP);
			return this.getName() + " Average: " + trimmedAvg + " % " + scale.getLetterGrade(100 * avg);

		}

	}
}
