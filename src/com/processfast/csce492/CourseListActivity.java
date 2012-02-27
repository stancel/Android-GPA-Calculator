package com.processfast.csce492;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class CourseListActivity extends Activity implements
		View.OnClickListener  {

	List<Course> courseList;

	CoursesDataSource source;

	Button addCourse, home;

	ListView list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.courselist);

		init();
	}

	public void init() {

		source = new CoursesDataSource(this);
		source.open();
		courseList = source.getAllCourses();

		

		addCourse = (Button) findViewById(R.id.btn_add_course);
		addCourse.setOnClickListener(this);
		home = (Button) findViewById(R.id.btn_home);
		home.setOnClickListener(this);

		list = (ListView) findViewById(R.id.courseList);

		ArrayAdapter<Course> adapter = new ArrayAdapter<Course> (this, android.R.layout.simple_list_item_1,
				android.R.id.text1, courseList);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int pos,
					long id) {
				onListItemClick(v, pos, id);
				
			}

			private void onListItemClick(View v, int pos, long id) {
				System.out.println("onListItemClick id=" + id);
				Intent i;
				i = new Intent(getApplicationContext(), IndividualCourseActivity.class);
				i.putExtra("id", courseList.get(pos).getId());
				startActivity(i);
			}
			
		});
		
		registerForContextMenu(list);
		source.close();
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
		if(v.getId() == R.id.courseList){
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
			
			menu.setHeaderTitle(courseList.get(info.position).getDept() + " " + courseList.get(info.position).getNumber());
			
			String[] menuItems = getResources().getStringArray(R.array.menu);
			
			for(int i = 0; i < menuItems.length; i ++){
				menu.add(Menu.NONE, i, i, menuItems[i]);
			}
		
		}
	}
	@Override
	public void onClick(View v) {
		Intent i;
		switch (v.getId()) {

		case R.id.btn_add_course:
			i = new Intent(this, AddCourseActivity.class);
			startActivity(i);
			//TODO create ON RESULT to automatically update the course list;
			
		
			break;
		case R.id.btn_home:
			finish();
			break;
		}

	}
	
	@Override
    public boolean onContextItemSelected(MenuItem item) {
	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	    int menuItemIndex = item.getItemId();
		String[] menuItems = getResources().getStringArray(R.array.menu);
		String menuItemName = menuItems[menuItemIndex];
	    String listItemId = courseList.get(info.position).getId()+"";

	    if (menuItemName == "Edit"){
	    	//TODO Move to course edit
	    }
	    if (menuItemName == "Delete"){
	    	//TODO move to course delete
	    }
	    System.out.println(String.format("Selected %s for item %s", menuItemName, listItemId));
    	return true;
    }
}
