package com.processfast.csce492;

import java.lang.reflect.Array;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;


public class AddGradeActivity extends Activity implements OnClickListener{
	
	private CoursesDataSource coursesSource;
	private AssignmentTypesSource typeSource;
	private GradesDataSource gradeSource;
	Button btnCancel, btnSubmit;
	Spinner spCourse, spType;
	EditText etName, etGradeNum, etGradeDen;
	String selectedName, selectedCourse, selectedType;
	Long selectedNum, selectedDen, finalGrade;
	
	@Override 
    public void onCreate(Bundle savedInstanceState) { 
          super.onCreate(savedInstanceState); 
          this.setContentView(R.layout.addgrade); 
          init();
          
          coursesSource = new CoursesDataSource(this);
          coursesSource.open();
          List<Course> courses = coursesSource.getAllCourses();
          
          int size = courses.size();
          
          ArrayAdapter <CharSequence> adapter1 =
        		  new ArrayAdapter <CharSequence> (this, android.R.layout.simple_spinner_item );
        		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        		 
          for(int i = 0 ; i < size ; i ++ ){
        	  adapter1.add(courses.get(i).toString());
          }
          
          typeSource = new AssignmentTypesSource(this);
          typeSource.open();    
          
          final ArrayAdapter <CharSequence> adapter2 =
        		new ArrayAdapter <CharSequence> (this, android.R.layout.simple_spinner_item );
        		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

          spCourse.setAdapter(adapter1);
          spCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView adapter1, View v, int i, long lng) {		
				selectedCourse = adapter1.getItemAtPosition(i).toString();
				List<AssignmentType> types = typeSource.getAllAssignmentTypesForCourse(course_id);
				int size2 = types.size();
				
		          for(int j = 0 ; j < size2 ; j ++ ){
		        	  adapter2.add(types.get(j).toString());
		          }
		          spType.setAdapter(adapter2);
		          spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		  			public void onItemSelected(AdapterView adapter2, View v, int i, long lng) {
						selectedType = adapter2.getItemAtPosition(i).toString();
		  			}
					public void onNothingSelected(AdapterView arg0) {
						// TODO I think nothing goes here				
					}
		          });
			}
			public void onNothingSelected(AdapterView arg0) {
				// TODO I think nothing goes here				
			}       	  
          });
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_AG_Submit:
			selectedName = etName.getText().toString();
			selectedNum = Long.valueOf(etGradeNum.getText().toString());
			selectedDen = Long.valueOf(etGradeDen.getText().toString());
			finalGrade = selectedNum / selectedDen;
			
			//TODO FINISH THIS SHIT.
	        Grade g = gradeSource.createGrade(7, 5, 100, "Final Exam", 95);
			break;
		case R.id.btn_AG_Cancel:
			finish();
			break;
		}	
	} 
	
	public void init() {
		btnCancel = (Button) findViewById(R.id.btn_AG_Cancel);
		btnSubmit = (Button) findViewById(R.id.btn_AG_Submit);
		spCourse = (Spinner) findViewById(R.id.spinner_AG_course);
		spType = (Spinner) findViewById(R.id.spinner_AG_type);
		etName = (EditText) findViewById(R.id.et_AG_name);
		etGradeNum = (EditText) findViewById(R.id.et_AG_numirator);
		etGradeDen = (EditText) findViewById(R.id.et_AG_denominator);
		
		btnCancel.setOnClickListener(this);
		btnSubmit.setOnClickListener(this);
	}
}
