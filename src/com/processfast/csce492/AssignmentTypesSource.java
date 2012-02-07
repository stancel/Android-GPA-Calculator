package com.processfast.csce492;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AssignmentTypesSource {
	// Database fields
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;

	private String[] allColumns = { DatabaseHelper.colTypeID,
			DatabaseHelper.colTypeCourseID, DatabaseHelper.colWeight,
			DatabaseHelper.colName };

	public AssignmentTypesSource(Context context){
		dbHelper = new DatabaseHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	/**
	 * The method adds an AssignmentType to the database.
	 * @author Byron Alleman
	 * @param course_id
	 * @param weight
	 * @param name
	 * @return
	 */
	public AssignmentType createAssignmentType(int course_id, int weight, String name){
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.colTypeCourseID, course_id);
		values.put(DatabaseHelper.colWeight, weight);
		values.put(DatabaseHelper.colName, name);
		
		long insertId = database.insert(DatabaseHelper.assignmentTypesTable, null, values);
		
		Log.i("AssignmentType", "Assignment Type \"" + name + "\" added to the database.");
		
		Cursor cursor = database.query(DatabaseHelper.assignmentTypesTable, allColumns,
				DatabaseHelper.colCourseID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		return cursorToAssignmentType(cursor);
	}
	
	/**
	 * Deletes an AssignmentType from the database
	 * 
	 * @author Byron Alleman
	 * @param course
	 *            The AssignmentType to be deleted
	 */
	public void deleteAssignmentType(AssignmentType type){
		int id = type.getId();
		Log.i("AssignmentType", "Assignment Type \"" + type.getName() + "\" deleted from the database.");
		database.delete(DatabaseHelper.assignmentTypesTable, DatabaseHelper.colTypeID + " = " + id, null);
	}
	
	
	/**
	 * This method returns the list of all AssignmentTypes for a given Course
	 * @author Byron Alleman
	 * @param course_id The course to list the assignment types for
	 * @return the list of assignment types
	 */
	public List<AssignmentType> getAllAssignmentTypesForCourse(int course_id){
		List<AssignmentType> types = new ArrayList<AssignmentType>();
		Cursor cursor = database.query(DatabaseHelper.assignmentTypesTable, 
				allColumns, DatabaseHelper.colTypeCourseID + " = " + course_id, 
				null, null, null, DatabaseHelper.colName);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()){
			types.add(cursorToAssignmentType(cursor));
			cursor.moveToNext();
		}
		cursor.close();
		return types;
	}
	
	/**
	 * This method returns the AssignmentType object that a passed Cursor object points to.
	 * @author Byron Alleman
	 * @param cursor
	 * @return The AssignmentType object that the Cursor points to
	 */
	private AssignmentType cursorToAssignmentType(Cursor cursor) {
		AssignmentType type = new AssignmentType();
		type.setId(cursor.getInt(0));
		type.setCourse_id(cursor.getInt(1));
		type.setWeight(cursor.getInt(2));
		type.setName(cursor.getString(3));
		
		return type;
	}
	
}
