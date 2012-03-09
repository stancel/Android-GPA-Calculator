package com.processfast.csce492;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity implements View.OnClickListener {
	
	Button bViewCourses, bAddCourse, bAddGrade, bViewStatistics;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.home);
		init();
	}

	//This is an onClick method that is called when one of the buttons is clicked.
	public void onClick(View view) {
		Intent i;
		//This is the switch case which picks the button that was pressed by its id.
		switch (view.getId()) {
		case R.id.bViewCourses:
			i = new Intent(this, CourseListActivity.class);
			startActivity(i);
			break;
		case R.id.bAddCourse:
			/**Start AddCourseActivity.java**/
			i = new Intent(this, AddCourseActivity.class);
			startActivity(i);
			break;
		case R.id.bAddGrade:
			/**Start AddGradeActivity.java**/
			i = new Intent(this, AddGradeActivity.class);
			startActivity(i);
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
