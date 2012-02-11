package com.processfast.csce492;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AddCourseActivity extends Activity implements View.OnClickListener {

	Button bAddAssignmentType, bSetUpScale, bCancel, bSubmit;
	EditText fAbbr, fNumber, fTeacher, fHours;
	ArrayList<AssignmentType> assignmentTypes;
	Course course;
	GradingScale scale;
	TextView types;

	private CoursesDataSource courseSource;
	private AssignmentTypesSource typeSource;

	// TODO remove this after code is connected to other screens
	int testCourseId = 5;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.addcourse);
		init();
	}

	@Override
	// This is an onClick method that is called when one of the buttons is
	// clicked.
	public void onClick(View view) {
		// This is the switch case which picks the button that was pressed by
		// its id.
		switch (view.getId()) {
		case R.id.bAddAssignmentType:
			Intent assType = new Intent(this, AddAssignmentTypeActivity.class);

			// Add the course activity to the intent
			startActivityForResult(assType, R.integer.ASSIGNMENT_TYPE_CREATE);

			break;

		case R.id.bSetUpScale:
			Intent scaleSetUp = new Intent(this, GradingScaleActivity.class);

			Log.i("General", "Sending scale to the Scale Setup activity.");
			scaleSetUp.putExtra("scale", scale);

			startActivityForResult(scaleSetUp, R.integer.SCALE_SETUP);

			break;

		case R.id.bCancel:
			finish();
			break;

		case R.id.bSubmit:
			// TODO Add Validation code
			Course course = new Course();

			course.setDept(fAbbr.getText().toString());
			course.setNumber(fNumber.getText().toString());
			course.setTeacher(fTeacher.getText().toString());
			course.setHours(Integer.parseInt(fHours.getText().toString()));
			int style;

			RadioGroup group1 = (RadioGroup) findViewById(R.id.radioGroup1);
			RadioButton checked = (RadioButton) group1.findViewById(group1
					.getCheckedRadioButtonId());
			if (checked.getId() == R.id.radio_percentage) {
				style = R.integer.STYLE_PERCENTAGE;
			} else {
				style = R.integer.STYLE_POINTS;
			}
			course.setStyle(style);

			// TODO Add grading scale
			course.setScale(scale);

			courseSource.open();
//			courseSource.createCourse(course.getDept(), course.getNumber(),
//					course.getTeacher(), course.getHours(), course.getStyle(),
//					course.getScale().getA(), course.getScale().getA_minus(),
//					course.getScale().getB_plus(), course.getScale().getB(),
//					course.getScale().getB_minus(), course.getScale()
//							.getC_plus(), course.getScale().getC(), course
//							.getScale().getC_minus(), course.getScale()
//							.getD_plus(), course.getScale().getD(), course
//							.getScale().getD_minus());
			course = courseSource.addCourseToDatabase(course);
			//courseSource.close();

			typeSource.open();
			for (int i = 0; i < assignmentTypes.size(); i++) {
				assignmentTypes.get(i).setCourse_id(course.getId());
				typeSource.addAssignmentTypeToDatabase(assignmentTypes.get(i));
			}
			

			//courseSource.open();
			List<Course> courses = courseSource.getAllCourses();

			for (int i = 0; i < courses.size(); i++) {
				Log.i("General", courses.get(i).toString());
				List<AssignmentType> t = typeSource.getAllAssignmentTypesForCourse(courses.get(i).getId());
				for(int j = 0; j < t.size(); j ++){
					Log.i("General", t.get(j).toString());
				}
			}
			courseSource.close();
			typeSource.close();

			finish();
			// Add course to database

			break;
		}

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case R.integer.ASSIGNMENT_TYPE_CREATE:
			if (resultCode == RESULT_OK) {
				AssignmentType type = new AssignmentType();
				type.setName(data.getStringExtra("TypeName"));
				type.setWeight(data.getIntExtra("TypeWeight", -1));
				assignmentTypes.add(type);
				String typeList = "";
				for (int i = 0; i < assignmentTypes.size(); i++) {
					if (i == 0) {
						typeList = assignmentTypes.get(i).getName() + " "
								+ assignmentTypes.get(i).getWeight() + "% \r\n";
					} else {
						typeList = typeList + assignmentTypes.get(i).getName()
								+ " " + assignmentTypes.get(i).getWeight()
								+ "% \r\n";
					}
				}
				if (typeList.length() > 0) {
					types.setText(typeList);
				}
				break;
			}
		case R.integer.SCALE_SETUP:
			if (resultCode == RESULT_OK) {
				scale = data.getParcelableExtra("scale");
				Log.i("General", scale.getA() + " ");
			}
			break;
		}
	}

	// This is a method to initialize all the variables.
	public void init() {
		bAddAssignmentType = (Button) findViewById(R.id.bAddAssignmentType);
		bCancel = (Button) findViewById(R.id.bCancel);
		bSubmit = (Button) findViewById(R.id.bSubmit);
		bSetUpScale = (Button) findViewById(R.id.bSetUpScale);
		fAbbr = (EditText) findViewById(R.id.field_dept_abr);
		fNumber = (EditText) findViewById(R.id.field_course_number);
		fTeacher = (EditText) findViewById(R.id.field_teacher_name);
		fHours = (EditText) findViewById(R.id.field_credit_hours);

		courseSource = new CoursesDataSource(this);
		typeSource = new AssignmentTypesSource(this);

		bAddAssignmentType.setOnClickListener(this);
		bCancel.setOnClickListener(this);
		bSubmit.setOnClickListener(this);
		bSetUpScale.setOnClickListener(this);

		assignmentTypes = new ArrayList<AssignmentType>();

		types = (TextView) findViewById(R.id.field_assignment_types);
		course = new Course();

		scale = new GradingScale();

	}
}
