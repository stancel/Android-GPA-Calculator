package com.processfast.csce492;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GradingScaleActivity extends Activity implements
		View.OnClickListener {
	Button bScaleSubmit;
	String courseName;
	
	EditText a, a_minus, b_plus, b, b_minus, c_plus, c, c_minus, d_plus, d, d_minus;
	String errors;
	GradingScale scale;
	AlertDialog errorMessage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.gradingscale);
		init();
	}

	@Override
	public void onClick(View v) {
		Log.i("General", "Submit button pressed.");
		switch (v.getId()) {
		case R.id.bScaleSubmit:
			Log.i("General", "Submit button pressed.");
			scale.setA(Util.getTextViewInteger(this, R.id.fScale_a));
			scale.setA_minus(Util.getTextViewInteger(this, R.id.fScale_a_minus));
			scale.setB_plus(Util.getTextViewInteger(this, R.id.fScale_b_plus));
			scale.setB(Util.getTextViewInteger(this, R.id.fScale_b));
			scale.setB_minus(Util.getTextViewInteger(this, R.id.fScale_b_minus));
			scale.setC_plus(Util.getTextViewInteger(this, R.id.fScale_c_plus));
			scale.setC(Util.getTextViewInteger(this, R.id.fScale_c));
			scale.setC_minus(Util.getTextViewInteger(this, R.id.fScale_c_minus));
			scale.setD_plus(Util.getTextViewInteger(this, R.id.fScale_d_plus));
			scale.setD(Util.getTextViewInteger(this, R.id.fScale_d));
			scale.setD_minus(Util.getTextViewInteger(this, R.id.fScale_d_minus));

			if (scale.isValidScale()) {
				Intent returnIntent = new Intent();
				returnIntent.putExtra("scale", scale);
				setResult(RESULT_OK, returnIntent);
				finish();

			} else {
				errorMessage = new AlertDialog.Builder(this).create();
				errorMessage.setTitle("Input error!");
				errorMessage
						.setMessage("You entered an invalid grading scale.");
				errorMessage.setButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								return;
							}
						});
				errorMessage.show();
			}
			break;
		}

	}

	// This is a method to initialize all the variables.
	public void init() {
		bScaleSubmit = (Button) findViewById(R.id.bScaleSubmit);

		bScaleSubmit.setOnClickListener(this);
		
		a = (EditText)findViewById(R.id.fScale_a);
		a_minus = (EditText)findViewById(R.id.fScale_a_minus);
		b_plus = (EditText)findViewById(R.id.fScale_b_plus);
		b = (EditText)findViewById(R.id.fScale_b);
		b_minus = (EditText)findViewById(R.id.fScale_b_minus);
		c_plus = (EditText)findViewById(R.id.fScale_c_plus);
		c = (EditText)findViewById(R.id.fScale_c);
		c_minus = (EditText)findViewById(R.id.fScale_c_minus);
		d_plus = (EditText)findViewById(R.id.fScale_d_plus);
		d = (EditText)findViewById(R.id.fScale_d);
		d_minus = (EditText)findViewById(R.id.fScale_d_minus);

		scale = (GradingScale) getIntent().getParcelableExtra("scale");
		
		courseName = getIntent().getStringExtra("courseName");
		
		if(courseName.equalsIgnoreCase(" ")){
			((TextView)findViewById(R.id.course_title)).setText(R.string.new_course);
		}
		else{
			((TextView)findViewById(R.id.course_title)).setText(courseName);
		}
		initializeTextFields();

		errors = "";
	}

	public void initializeTextFields() {
		if (scale.getA() != -1)
			a.setText(""+scale.getA());
		if (scale.getA_minus() != -1)
			a_minus.setText(""+scale.getA_minus());
		if (scale.getB_plus() != -1)
			b_plus.setText(""+scale.getB_plus());
		if (scale.getB() != -1)
			b.setText(""+scale.getB());
		if (scale.getB_minus() != -1)
			b_minus.setText(""+scale.getB_minus());
		if (scale.getC_plus() != -1)
			c_plus.setText(""+scale.getC_plus());
		if (scale.getC() != -1)
			c.setText(""+scale.getC());
		if (scale.getC_minus() != -1)
			c_minus.setText(""+scale.getC_minus());
		if (scale.getD_plus() != -1)
			d_plus.setText(""+scale.getD_plus());
		if (scale.getD() != -1)
			d.setText(""+scale.getD());
		if (scale.getD_minus() != -1)
			d_minus.setText(""+scale.getD_minus());
	}

}