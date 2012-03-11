package com.processfast.csce492;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DeleteGradeActivity extends Activity {

	GradesDataSource gradeSource;

	Button bCancel, bConfirm;

	TextView gradeName;

	int id;
	String name;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.deletegrade);
		
		init();
	}

	public void init() {

		id = getIntent().getIntExtra("id", -1);
		name = getIntent().getStringExtra("name");

		gradeSource = new GradesDataSource(this);
		gradeSource.open();

		bCancel = (Button) findViewById(R.id.bCancel);
		bCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				finish();
			}
		});

		gradeName = (TextView) findViewById(R.id.dlt_grade_lbl);
		gradeName.setText(name);
		bConfirm = (Button) findViewById(R.id.bConfirm);
		bConfirm.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				gradeSource.deleteGrade(id);
				Intent returnIntent = new Intent();
				setResult(RESULT_OK, returnIntent);
				finish();
			}
		});
	}

}
