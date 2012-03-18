package com.processfast.csce492;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DeleteAssignmentTypeActivity extends Activity {
	
	AssignmentTypesSource typeSource;

	Button bCancel, bConfirm;
	
	TextView typeName;
	
	int id;
	String name;
	
	@Override 
    public void onCreate(Bundle savedInstanceState) { 
          super.onCreate(savedInstanceState); 
          this.setContentView(R.layout.deleteassignmenttype);  
          
          init();
	}


	public void init() {

		id = getIntent().getIntExtra("id", -1);
		name = getIntent().getStringExtra("name");
		
		typeSource = new AssignmentTypesSource(this);
		typeSource.open();

		bCancel = (Button) findViewById(R.id.bCancel);
		bCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				finish();
			}
		});

		typeName = (TextView) findViewById(R.id.dlt_type_lbl);
		typeName.setText(name);
		bConfirm = (Button) findViewById(R.id.bConfirm);
		bConfirm.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				typeSource.deleteAssignmentType(id);
				Intent returnIntent = new Intent();
				setResult(RESULT_OK, returnIntent);
				finish();
			}
		});
	}

}
