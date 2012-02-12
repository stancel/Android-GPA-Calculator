package com.processfast.csce492;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddAssignmentTypeActivity extends Activity implements
		View.OnClickListener {

	Button bSubmit, bCancel;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.addassignmenttype);

		init();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bCancel:
			finish();
			break;

		case R.id.bSubmit:
			String name = ((TextView)findViewById(R.id.fName)).getText().toString();
			int weight = Integer.parseInt(((TextView)findViewById(R.id.fWeight)).getText().toString());
			Intent returnIntent = new Intent();
			returnIntent.putExtra("TypeName", name);
			returnIntent.putExtra("TypeWeight", weight);
			setResult(RESULT_OK, returnIntent);
			finish();
			break;
		}
	}
	
	// This is a method to initialize all the variables.
	public void init() {
		bSubmit = (Button) findViewById(R.id.bSubmit);
		bCancel = (Button) findViewById(R.id.bCancel);
		
		bSubmit.setOnClickListener(this);
		bCancel.setOnClickListener(this);
	}

}
