package com.processfast.csce492;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StatisticsActivity extends Activity implements OnClickListener {

	Button btnHome;
	TextView credits, GPA, numOfGrades, usageFreq;
	private CoursesDataSource coursesSource;
	//private AssignmentTypesSource typeSource;
	private GradesDataSource gradeSource;
	List<Grade> listOfGrades;
	List<Course> listOfCourses;
	int totalCredits;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.statistics);
		init();
		setNumOfCredits();
		setNumberOfGrades();
		//We still need the GPA and the usage frequency
	}

	//Gets the list of courses and then adds up all the hours for them.
	private void setNumOfCredits() {
		coursesSource = new CoursesDataSource(this);
		coursesSource.open();
		listOfCourses = coursesSource.getAllCourses();
		int size = listOfCourses.size();

		for (int i = 0; i < size; i++) {
			totalCredits =+ listOfCourses.get(i).getHours();
		}
		
		credits.setText(totalCredits);
	}

	//Gets the list of all the grades and then determines the number of grades.
	private void setNumberOfGrades() {
		gradeSource = new GradesDataSource(this);
		gradeSource.open();
		listOfGrades = gradeSource.getAllGrades();
		int numberOfGrades = listOfGrades.size();	
		numOfGrades.setText(numberOfGrades);
	}

	//Starts HomeActivity
	public void onClick(View v) {
		Intent home = new Intent(this, HomeActivity.class);
		startActivity(home);
	}

	public void init() {
		btnHome = (Button) findViewById(R.id.btn_STAT_Home);

		credits = (TextView) findViewById(R.id.tv_STAT_credits);
		GPA = (TextView) findViewById(R.id.tv_STAT_GPA);
		numOfGrades = (TextView) findViewById(R.id.tv_STAT_NumberOfGrades);
		usageFreq = (TextView) findViewById(R.id.tv_STAT_UsageFreq);

		btnHome.setOnClickListener(this);
	}

}
