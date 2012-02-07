package com.processfast.csce492;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {
	
	private View.OnClickListener addCourseListener = new View.OnClickListener(){
		@Override
		public void onClick(View v) {
			Intent i = new Intent(v.getContext(), AddCourseActivity.class);
			startActivity(i);
			
		}
		
	};
	
	@Override 
    public void onCreate(Bundle savedInstanceState) { 
          super.onCreate(savedInstanceState); 
          this.setContentView(R.layout.home);  
          
          Button addCourse = (Button)findViewById(R.id.bAddCourse);
          
          addCourse.setOnClickListener(addCourseListener);
          
    } 
	
	
}
