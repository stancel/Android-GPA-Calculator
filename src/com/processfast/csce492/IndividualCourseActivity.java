package com.processfast.csce492;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IndividualCourseActivity extends Activity implements
		View.OnClickListener {
	int courseID;

	CoursesDataSource courseSource;
	AssignmentTypesSource typeSource;
	GradesDataSource gradeSource;

	Course course;
	List<AssignmentType> types;
	
	int typePosition;

	LinearLayout typeLayout;
	Button addType, back;
	TextView courseTitle, currentGrade;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.individualcourse);

		System.out.println(getIntent().getIntExtra("id", -1));

		init();
	}

	public void init() {
		courseID = getIntent().getIntExtra("id", -1);

		typeLayout = (LinearLayout) findViewById(R.id.typelayout);

		back = (Button) findViewById(R.id.btn_back);
		back.setOnClickListener(this);

		courseTitle = (TextView) findViewById(R.id.coursetitle);
		currentGrade = (TextView) findViewById(R.id.currentaverage);

		// Open Sources
		courseSource = new CoursesDataSource(this);
		courseSource.open();
		typeSource = new AssignmentTypesSource(this);
		typeSource.open();
		gradeSource = new GradesDataSource(this);

		course = courseSource.getCourseByID(courseID);
		System.out.println(course.toString());

		types = typeSource.getAllAssignmentTypesForCourse(courseID);

		// Close Sources
		typeSource.close();
		courseSource.close();

		// Set the course title
		courseTitle.setText(course.getCourseTitle());
		currentGrade.setText(course.getCourseAverageString());
		
		update();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case R.integer.ASSIGNMENT_TYPE_CREATE:
			if (resultCode == RESULT_OK) {
				update();
			}
			break;
		}
	}
	public void update(){
		// For each assignment type, generate a view 
		for (int i = 0; i < types.size(); ++i){
			typePosition = i;
			LinearLayout individual = new LinearLayout(this);
			individual.setOrientation(LinearLayout.VERTICAL);
			TextView typeAverage = new TextView(this);
			typeAverage.setTextAppearance(this, android.R.style.TextAppearance_Medium);
			typeAverage.setText("\t" + types.get(i).getAssignmentTypeAverage());
			// Set the View's tag to the Assignment Type object
			typeAverage.setTag(types.get(i));
			individual.addView(typeAverage);
			
			// Get Grades
			gradeSource.open();
			List <Grade> grades = gradeSource.getAllGradesByAssignmentType(types.get(i).getId());
			for (int j = 0; j < grades.size(); ++j){
				TextView gradeView = new TextView(this);
				gradeView.setTextAppearance(this, android.R.style.TextAppearance_Small);
				gradeView.setText(grades.get(i).toString());
				gradeView.setTag(grades.get(i));
				individual.addView(gradeView);
			}
			gradeSource.close();
			typeLayout.addView(individual);
			
			Button addGrade = new Button(this);
			addGrade.setText(R.string.lbl_add_grade);
			addGrade.setTag(types.get(i));
			addGrade.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			addGrade.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent a = new Intent(getApplicationContext(), AddGradeActivity.class);
					a.putExtra("courseID", course.getId());
					a.putExtra("assignmentTypeID", ((AssignmentType)v.getTag()).getId());
					startActivityForResult(a, R.integer.GRADE_ADD);
				}
			});
			
			typeLayout.addView(addGrade);
		}
	}
}
