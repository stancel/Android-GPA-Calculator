package com.processfast.csce492;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AddCourseActivity extends Activity implements View.OnClickListener {

	Button bAddAssignmentType, bCancel, bSubmit;
	ArrayList<AssignmentType> assignmentTypes;
	TextView types;

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
			Intent i = new Intent(this, AddAssignmentTypeActivity.class);
			Bundle b = new Bundle();

			// Add the course activity to the intent
			b.putInt("courseId", testCourseId);
			i.putExtras(b);

			startActivityForResult(i, R.integer.ASSIGNMENT_TYPE_CREATE);

			break;

		case R.id.bCancel:

			break;

		case R.id.bSubmit:
			//TODO Add Validation code
			Course course = new Course();
			
			String title = ((TextView)findViewById(R.id.field_course_title)).toString();
			String abbr = ((TextView)findViewById(R.id.field_dept_abr)).toString();
			String number = ((TextView)findViewById(R.id.field_course_number)).toString();
			String teacher = ((TextView)findViewById(R.id.field_teacher_name)).toString();
			int hours = Integer.parseInt(((TextView)findViewById(R.id.field_credit_hours)).toString());

			RadioGroup group1 = (RadioGroup)findViewById(R.id.radioGroup1);
			RadioButton checked = (RadioButton)group1.findViewById(group1.getCheckedRadioButtonId());
			if(checked.getId() == R.id.radio_percentage){
				int style = R.integer.STYLE_PERCENTAGE;
			}
			else{
				int style = R.integer.STYLE_POINTS;
			}
			
			
			//TODO Add grading scale
			
			break;
		}

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 switch (requestCode){
		 case  R.integer.ASSIGNMENT_TYPE_CREATE:
			 if(resultCode == RESULT_OK){
			 AssignmentType type = new AssignmentType();
			 type.setName(data.getStringExtra("TypeName"));
             type.setWeight(data.getIntExtra("TypeWeight", -1));
             assignmentTypes.add(type);
             String typeList = "";
             for(int i = 0; i < assignmentTypes.size(); i ++ ){
            	 if(i == 0){
            		 typeList = assignmentTypes.get(i).getName() + " " + assignmentTypes.get(i).getWeight() + "% \r\n";
            	 }
            	 else{
            		 typeList = typeList + assignmentTypes.get(i).getName() + " " + assignmentTypes.get(i).getWeight() + "% \r\n";
            	 }
             }
             if(typeList.length() > 0){
            	 types.setText(typeList);
             }
             break;
			 }
         }
	}

	// This is a method to initialize all the variables.
	public void init() {
		bAddAssignmentType = (Button) findViewById(R.id.bAddAssignmentType);
		bCancel = (Button) findViewById(R.id.bCancel);
		bSubmit = (Button) findViewById(R.id.bSubmit);

		bAddAssignmentType.setOnClickListener(this);
		bCancel.setOnClickListener(this);
		bSubmit.setOnClickListener(this);

		assignmentTypes = new ArrayList<AssignmentType>();
		
		types = (TextView) findViewById(R.id.field_assignment_types);

	}
}
