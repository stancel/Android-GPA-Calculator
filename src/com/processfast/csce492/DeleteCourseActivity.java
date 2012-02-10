package com.processfast.csce492;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DeleteCourseActivity extends Activity implements OnClickListener {
	
	
	Button bCancel, bConfirm;
	
    public void onCreate(Bundle savedInstanceState) { 
          super.onCreate(savedInstanceState); 
          this.setContentView(R.layout.deletecourse);  
          
          bCancel = (Button)	findViewById(R.id.bCancel);
          bCancel.setOnClickListener(new OnClickListener()
          {
        	  public void onClick(View view){
        		  //code to return to previous screen and not delete
        		  System.out.println("Test for the Cancel button.");
        	  }
          });
          
  		  bConfirm = (Button) findViewById(R.id.bConfirm);
  		  bConfirm.setOnClickListener(new OnClickListener()
  		  {
  			  public void onClick(View view){
  				  //code to delete course from db  
  				  System.out.println("Test for the Confirm button.");
  			  }
  		  });
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	

}
