package com.processfast.csce492;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IndividualCourseActivity extends Activity implements
		View.OnClickListener {
	int courseID;

	CoursesDataSource courseSource;
	AssignmentTypesSource typeSource;
	GradesDataSource gradeSource;

	Course course;
	List<AssignmentType> types;

	TextView selectedGrade;
	int typePosition;

	LinearLayout typeLayout;
	LinearLayout weightLayout;
	Button addType, back;
	TextView courseTitle, currentGrade;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.individualcourse);

		System.out.println(getIntent().getIntExtra("id", -1));

		init();
	}

	public void init() {
		courseID = getIntent().getIntExtra("id", -1);

		typeLayout = (LinearLayout) findViewById(R.id.typelayout);
		weightLayout = (LinearLayout) findViewById(R.id.layout_weights);

		back = (Button) findViewById(R.id.btn_back);
		back.setOnClickListener(this);

		courseTitle = (TextView) findViewById(R.id.coursetitle);
		currentGrade = (TextView) findViewById(R.id.currentaverage);

		// Open Sources
		courseSource = new CoursesDataSource(this);
		courseSource.open();
		typeSource = new AssignmentTypesSource(this);
		typeSource.open();
		gradeSource = new GradesDataSource(this);

		course = courseSource.getCourseByID(courseID);
		course.setContext(this);

		types = typeSource.getAllAssignmentTypesForCourse(courseID);

		setWeightLayout();

		// Close Sources
		typeSource.close();
		courseSource.close();

		// Set the course title
		courseTitle.setText(course.getCourseTitle());

		update();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		}
	}

	public void setWeightLayout() {
		for (int i = 0; i < types.size(); ++i) {
			TextView indWeight = new TextView(this);
			indWeight.setText("\t\t" + types.get(i).toString());
			indWeight.setTextAppearance(this,
					android.R.style.TextAppearance_Small);
			weightLayout.addView(indWeight);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case R.integer.GRADE_ADD:
			if (resultCode == RESULT_OK) {
				update();
			}
			break;

		case R.integer.GRADE_DELETE:
			if (resultCode == RESULT_OK) {
				update();
			}
			break;
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		int menuItemIndex = item.getItemId();
		
		String[] menuItems = getResources().getStringArray(R.array.menu);
		String menuItemName = menuItems[menuItemIndex];

		Intent i;
		if (menuItemName.equals("Edit")) {
			// TODO Move to Assignment Type edit
			System.out.println("Edit course");
		}
		if (menuItemName.equals("Delete")) {
			System.out.println("Delete course");
			i = new Intent(this, DeleteGradeActivity.class);
			i.putExtra("id", ((Grade) selectedGrade.getTag()).getId()); 
			i.putExtra("name", ((Grade)selectedGrade.getTag()).getName());
			startActivityForResult(i, R.integer.GRADE_DELETE);
		}
		return true;
	}

	public void update() {
		typeLayout.removeAllViews();
		// For each assignment type, generate a view
		for (int i = 0; i < types.size(); ++i) {
			typePosition = i;
			LinearLayout individual = new LinearLayout(this);
			individual.setOrientation(LinearLayout.VERTICAL);
			TextView typeAverage = new TextView(this);
			typeAverage.setTextAppearance(this,
					android.R.style.TextAppearance_Medium);
			typeAverage.setText("\t"
					+ types.get(i).getAssignmentTypeAverage(this, course));
			//typeAverage.setClickable(true);
			// typeAverage.setTextColor(R.drawable.text_color);
			// Set the View's tag to the Assignment Type object
			typeAverage.setTag(types.get(i));
			/*typeAverage
					.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

						@Override
						public void onCreateContextMenu(ContextMenu menu,
								View v, ContextMenuInfo menuInfo) {

							menu.setHeaderTitle(((AssignmentType) v.getTag())
									.getName());

							String[] menuItems = getResources().getStringArray(
									R.array.menu);

							for (int i = 0; i < menuItems.length; i++) {
								menu.add(Menu.NONE, i, i, menuItems[i]);
							}

						}
					});*/
			individual.addView(typeAverage);

			// Get Grades
			gradeSource.open();
			List<Grade> grades = gradeSource.getAllGradesByAssignmentType(types
					.get(i).getId());
			for (int j = 0; j < grades.size(); ++j) {
				TextView gradeView = new TextView(this);
				gradeView.setTextAppearance(this,
						android.R.style.TextAppearance_Small);
				gradeView.setText(grades.get(j).toString());
				gradeView.setClickable(true);
				gradeView.setTag(grades.get(j));
				gradeView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

					@Override
					public void onCreateContextMenu(ContextMenu menu,
							View v, ContextMenuInfo menuInfo) {

						menu.setHeaderTitle(((Grade) v.getTag())
								.getName());
						
						selectedGrade = (TextView) v;
						String[] menuItems = getResources().getStringArray(
								R.array.menu);

						for (int i = 0; i < menuItems.length; i++) {
							menu.add(Menu.NONE, i, i, menuItems[i]);
						}

					}
				});
				individual.addView(gradeView);
			}
			gradeSource.close();
			typeLayout.addView(individual);

			Button addGrade = new Button(this);
			addGrade.setText(R.string.lbl_add_grade);
			addGrade.setTag(types.get(i));
			addGrade.setLayoutParams(new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			addGrade.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent a = new Intent(getApplicationContext(),
							AddGradeActivity.class);
					a.putExtra("courseID", course.getId());
					a.putExtra("assignmentTypeID",
							((AssignmentType) v.getTag()).getId());
					startActivityForResult(a, R.integer.GRADE_ADD);
				}
			});
			currentGrade.setText(course.getCourseCurrentGrade());
			typeLayout.addView(addGrade);
		}
	}
}
