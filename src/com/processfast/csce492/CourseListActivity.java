package com.processfast.csce492;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
// import java.util.ArrayList;
import java.util.List;

public class CourseListActivity extends ListActivity {
	
	Button addCourse, home;
	TextView displayCourses;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.courselist);
		setListAdapter
		
		addCourse = (Button) findViewById(R.id.btnListAddCourse);
		home = (Button) findViewById(R.id.btnListHome); 
		
		displayCourses = (TextView) findViewById(R.id.tvCourseDisplay);	
		
		displayCourses.setText(text);
		CoursesDataSource courseDataObj = new CoursesDataSource(this);
		courseDataObj.open();
		List<Course> courses = courseDataObj.getAllCourses();
		
		for (int i=0; i < courses.size(); i++) {
			System.out.println(courses.get(i).toString());
		}
		
		home.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent toHome = new Intent(CourseListActivity.this, HomeActivity.class);
				startActivity(toHome);		
			}
		});
		
		addCourse.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent toAddCourse = new Intent(CourseListActivity.this, AddCourseActivity.class);
				startActivity(toAddCourse);		
			}
		});
		
		
		
	}
	
	
	
	
	
	
}
