package com.processfast.csce492;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DeleteCourseActivity extends Activity {
	CoursesDataSource courseSource;

	Button bCancel, bConfirm;
	
	int id;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.deletecourse);

		init();
	}


	public void init() {

		id = getIntent().getIntExtra("id", -1);
		courseSource = new CoursesDataSource(this);
		courseSource.open();

		bCancel = (Button) findViewById(R.id.bCancel);
		bCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				finish();
			}
		});

		bConfirm = (Button) findViewById(R.id.bConfirm);
		bConfirm.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				courseSource.deleteCourse(id);
				Intent returnIntent = new Intent();
				setResult(RESULT_OK, returnIntent);
				finish();
			}
		});
	}

}
