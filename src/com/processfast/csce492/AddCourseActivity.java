package com.processfast.csce492;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class AddCourseActivity extends Activity implements View.OnClickListener {

	Button bAddAssignmentType, bSetUpScale, bCancel, bSubmit;
	RadioGroup radios;
	RadioButton percentage, point;
	EditText fAbbr, fNumber, fTeacher, fHours;
	ArrayList<AssignmentType> assignmentTypes;
	Course course;
	GradingScale scale;
	TextView types;
	AlertDialog errorMessage;
	int gradingStyle;

	private CoursesDataSource courseSource;
	private AssignmentTypesSource typeSource;

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
		String courseName = fAbbr.getText().toString() + " " + fNumber.getText().toString();
		// This is the switch case which picks the button that was pressed by
		// its id.
		switch (view.getId()) {
		case R.id.bAddAssignmentType:
			Intent assType = new Intent(this, AddAssignmentTypeActivity.class);

			if (radios.getCheckedRadioButtonId() == point.getId()) {
				assType.putExtra("style", R.integer.STYLE_POINTS);
			} else {
				assType.putExtra("style", R.integer.STYLE_PERCENTAGE);
			}
			
			assType.putExtra("courseName", courseName);

			// Add the course activity to the intent
			startActivityForResult(assType, R.integer.ASSIGNMENT_TYPE_CREATE);

			break;

		case R.id.bSetUpScale:
			Intent scaleSetUp = new Intent(this, GradingScaleActivity.class);

			Log.i("General", "Sending scale to the Scale Setup activity.");
			scaleSetUp.putExtra("scale", scale);
			scaleSetUp.putExtra("courseName", courseName);

			startActivityForResult(scaleSetUp, R.integer.SCALE_SETUP);

			break;

		case R.id.bCancel:
			finish();
			break;

		case R.id.bSubmit:
			if (validateCourse()) {
				storeCourseInformation();
				finish();
			}
			break;
		}

	}

	private boolean validateCourse() {
		String errors = "You have made the following error(s): \n";
		boolean inputError = false;
		// ensure dept abbreviation is not blank

		if (fAbbr.getText().toString().length() == 0) {
			errors += "-Department Abbreviation cannot be left blank.\n";
			inputError = true;
		}

		// ensure course number is not blank
		if (fNumber.getText().toString().length() == 0) {
			errors += "-Course number cannot be left blank.\n";
			inputError = true;
		}
		/*
		 * Ensure assignment type weights add up to 100% or 0% for percentage
		 * based courses
		 */
		if (gradingStyle == R.integer.STYLE_PERCENTAGE) {
			int weightSum = 0;
			for (int i = 0; i < assignmentTypes.size(); ++i) {
				weightSum += assignmentTypes.get(i).getWeight();
			}
			if (weightSum != 0 && weightSum != 100) {
				errors += "-Assignment type weights must add up to 100%. \n";
				inputError = true;
			}
		}

		// Ensure grading scale is set up.
		if (!scale.isValidScale()) {
			errors += "-Grading scale must be set up.\n";
			inputError = true;
		}

		if (inputError == true) {
			errorMessage.setMessage(errors);
			errorMessage.show();
			return false;
		}

		return true;
	}

	@Override
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

		radios = (RadioGroup) findViewById(R.id.radioGroup1);

		percentage = (RadioButton) findViewById(R.id.radio_percentage);
		point = (RadioButton) findViewById(R.id.radio_points);

		courseSource = new CoursesDataSource(this);
		typeSource = new AssignmentTypesSource(this);

		gradingStyle = R.integer.STYLE_PERCENTAGE;
		bAddAssignmentType.setOnClickListener(this);
		bCancel.setOnClickListener(this);
		bSubmit.setOnClickListener(this);
		bSetUpScale.setOnClickListener(this);

		assignmentTypes = new ArrayList<AssignmentType>();

		types = (TextView) findViewById(R.id.field_assignment_types);
		course = new Course();

		scale = new GradingScale();

		errorMessage = new AlertDialog.Builder(this).create();
		errorMessage.setTitle("Input error!");
		errorMessage.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		});

		/*
		 * This ensures that if the course grading style is changed the
		 * assignment types are cleared.
		 */
		radios.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				assignmentTypes.clear();
				types.setText(R.string.none);
				RadioGroup group1 = (RadioGroup) findViewById(R.id.radioGroup1);
				RadioButton checked = (RadioButton) group1.findViewById(group1
						.getCheckedRadioButtonId());
				if (checked.getId() == R.id.radio_percentage) {
					gradingStyle = R.integer.STYLE_PERCENTAGE;
				} else {
					gradingStyle = R.integer.STYLE_POINTS;
				}
			}
		});

	}

	public void storeCourseInformation() {
		Course course = new Course();

		course.setDept(fAbbr.getText().toString());
		course.setNumber(fNumber.getText().toString());
		course.setTeacher(fTeacher.getText().toString());
		course.setHours(Integer.parseInt(fHours.getText().toString().trim()));

		course.setStyle(gradingStyle);

		// Add grading scale
		course.setScale(scale);

		// Add the course to the database
		courseSource.open();
		course = courseSource.addCourseToDatabase(course);
		courseSource.close();

		// Add the assignment types to the database
		typeSource.open();
		for (int i = 0; i < assignmentTypes.size(); i++) {
			assignmentTypes.get(i).setCourse_id(course.getId());
			typeSource.addAssignmentTypeToDatabase(assignmentTypes.get(i));
		}

		typeSource.close();
		
	}
}