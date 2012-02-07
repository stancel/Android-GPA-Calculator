package com.processfast.csce492;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity implements OnClickListener {
	
	Button bViewCourses, bAddCourse, bAddGrade, bViewStatistics;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.home);
		init();
	}

	//This is an onClick method that is called when one of the buttons is clicked.
	public void onClick(View view) {
		//This is the switch case which picks the button that was pressed by its id.
		switch (view.getId()) {
		case R.id.bViewCourses:
			/**Start CourseListActivity.java**/
			break;
		case R.id.bAddCourse:
			/**Start AddCourseActivity.java**/
			break;
		case R.id.bAddGrade:
			/**Start AddGradeActivity.java**/
			break;		
		case R.id.bViewStatistics:
			/**Start StatisticsActivity.java**/
			break;
		}
	}
	
	//This is a method to initialize all the variables.
	public void init() {
		bViewCourses = (Button) findViewById(R.id.bViewCourses);
		bAddCourse = (Button) findViewById(R.id.bAddCourse);
		bAddGrade = (Button) findViewById(R.id.bAddGrade);
		bViewStatistics = (Button) findViewById(R.id.bViewStatistics);
		
		bViewCourses.setOnClickListener(this);
		bAddCourse.setOnClickListener(this);
		bAddGrade.setOnClickListener(this);
		bViewStatistics.setOnClickListener(this);
	}
}
