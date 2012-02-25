package com.processfast.csce492;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class CourseListActivity extends Activity implements
		View.OnClickListener  {

	List<Course> courseList;

	CoursesDataSource source;

	Button addCourse, home;

	ListView list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.courselist);

		init();
	}

	public void init() {

		source = new CoursesDataSource(this);
		source.open();
		courseList = source.getAllCourses();

		

		addCourse = (Button) findViewById(R.id.btn_add_course);
		addCourse.setOnClickListener(this);
		home = (Button) findViewById(R.id.btn_home);
		home.setOnClickListener(this);

		list = (ListView) findViewById(R.id.courseList);

		ArrayAdapter<Course> adapter = new ArrayAdapter<Course> (this, android.R.layout.simple_list_item_1,
				android.R.id.text1, courseList);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int pos,
					long id) {
				onListItemClick(v, pos, id);
				
			}

			private void onListItemClick(View v, int pos, long id) {
				System.out.println("onListItemClick id=" + id);
				// TODO continue here.
			}
			
		});
		source.close();
	}

	@Override
	public void onClick(View v) {
		Intent i;
		switch (v.getId()) {

		case R.id.btn_add_course:
			i = new Intent(this, AddCourseActivity.class);
			startActivity(i);
			//TODO create ON RESULT to automatically update the course list;
			
		
			break;
		case R.id.btn_home:
			finish();
			break;
		}

	}
	
	
	public void refreshList(){
		source.open();
		courseList = source.getAllCourses();
		list = (ListView) findViewById(R.id.courseList);

		ArrayAdapter<Course> adapter = new ArrayAdapter<Course> (this, android.R.layout.simple_list_item_1,
				android.R.id.text1, courseList);
		list.setAdapter(adapter);
		source.close();
	}
}
