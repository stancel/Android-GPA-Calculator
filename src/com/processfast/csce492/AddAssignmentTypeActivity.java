package com.processfast.csce492;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddAssignmentTypeActivity extends Activity implements
		View.OnClickListener {

	Button bSubmit, bCancel;

	AlertDialog errorMessage;

	int style;

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

			if (validateAssignmentType()) {
				String name = ((TextView) findViewById(R.id.fName)).getText()
						.toString();
				int weight = Integer
						.parseInt(((TextView) findViewById(R.id.fWeight))
								.getText().toString());
				Intent returnIntent = new Intent();
				returnIntent.putExtra("TypeName", name);
				returnIntent.putExtra("TypeWeight", weight);
				setResult(RESULT_OK, returnIntent);
				finish();

			}
			break;
		}
	}

	// This is a method to initialize all the variables.
	public void init() {
		bSubmit = (Button) findViewById(R.id.bSubmit);
		bCancel = (Button) findViewById(R.id.bCancel);

		bSubmit.setOnClickListener(this);
		bCancel.setOnClickListener(this);

		style = getIntent().getIntExtra("style", R.integer.STYLE_PERCENTAGE);

		// Ensure that if it is a point based system that the text changes.
		if (style == R.integer.STYLE_POINTS) {
			((TextView) findViewById(R.id.lPercent)).setText(R.string.lPoints);
		}

		String courseName = getIntent().getStringExtra("courseName");

		if (courseName.equalsIgnoreCase(" ")) {
			((TextView) findViewById(R.id.lCourse))
					.setText(R.string.new_course);
		} else {
			((TextView) findViewById(R.id.lCourse)).setText(courseName);
		}
		errorMessage = new AlertDialog.Builder(this).create();
		errorMessage.setTitle("Input error!");
		errorMessage.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		});
	}

	public boolean isValidWeight() {
		int weight = Integer.parseInt(((TextView) findViewById(R.id.fWeight))
				.getText().toString());
		if (style == R.integer.STYLE_PERCENTAGE) {
			if (weight > 100 || weight < 0) {
				return false;
			}
		}

		return true;
	}

	private boolean validateAssignmentType() {
		String errors = "You have made the following error(s): \n";
		boolean inputError = false;

		int weight = 0;

		if (((TextView) findViewById(R.id.fWeight)).getText().toString().trim()
				.length() > 0) {
			weight = Integer.parseInt(((TextView) findViewById(R.id.fWeight))
					.getText().toString().trim());

		}

		if (((TextView) findViewById(R.id.fName)).getText().toString().trim()
				.length() == 0) {
			errors += "-Assignment type name must not be left blank.\n";
			inputError = true;
		}
		if (style == R.integer.STYLE_POINTS) {
			if (weight < 1) {
				errors += "-Assignment type weight must be a positive number.";
				inputError = true;
			}
		} else {
			if (weight < 1 || weight > 100) {
				errors += "-Assignment type weight must be between 1 and 100";
				inputError = true;
			}
		}

		if (inputError == true) {
			errorMessage.setMessage(errors);
			errorMessage.show();
			return false;
		}

		return true;
	}
}