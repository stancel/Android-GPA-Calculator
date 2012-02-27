package com.processfast.csce492;

import android.app.Activity;
import android.os.Bundle;

public class IndividualCourseActivity extends Activity {
	@Override 
    public void onCreate(Bundle savedInstanceState) { 
          super.onCreate(savedInstanceState); 
          this.setContentView(R.layout.individualcourse);  
          
          System.out.println(getIntent().getIntExtra("id", -1));
    } 
}
