package com.processfast.csce492;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class GradesDataSource {
	// Database fields
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;

	private String[] allColumns = { DatabaseHelper.colGradeID,
			DatabaseHelper.colGradeTypeID, DatabaseHelper.colGradeCourseID,
			DatabaseHelper.colMaxGrade, DatabaseHelper.colGradeName,
			DatabaseHelper.colGrade, DatabaseHelper.colGradeDate };

	public GradesDataSource(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	/**
	 * This method adds a grade to the database
	 * 
	 * @author Byron Alleman
	 * @param type
	 * @param course
	 * @param max
	 * @param name
	 * @param grade
	 * @return a cursor to the newly created grade
	 */
	public Grade createGrade(int type, int course, float max, String name,
			float grade) {
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.colGradeTypeID, type);
		values.put(DatabaseHelper.colGradeCourseID, course);
		values.put(DatabaseHelper.colMaxGrade, max);
		values.put(DatabaseHelper.colGradeName, name);
		values.put(DatabaseHelper.colGrade, grade);

		long insertId = database.insert(DatabaseHelper.gradesTable, null,
				values);

		Log.i("Grade", "Grade \"" + name + "\" added to database.");
		Cursor cursor = database.query(DatabaseHelper.gradesTable, allColumns,
				DatabaseHelper.colGradeID + " = " + insertId, null, null, null,
				null);
		cursor.moveToFirst();
		return cursorToGrade(cursor);
	}

	/**
	 * Deletes a grade from the database
	 * 
	 * @author Byron Alleman
	 * @param grade
	 *            The grade to be deleted
	 */
	public void deleteGrade(Grade grade) {
		int id = grade.getId();
		Log.i("Grade", "Grade # " + id + " is deleted.");

		database.delete(DatabaseHelper.gradesTable, DatabaseHelper.colGradeID
				+ " = " + id, null);
	}


	/**
	 * Returns a list of all grades with a given assignment type
	 * organized by time stamp.
	 * @author Byron Alleman
	 * @param type the type of assignment
	 * @return the list of all grades with a given assignment type
	 */
	public List<Grade> getAllGradesByAssignmentType(int type) {
		List<Grade> courses = new ArrayList<Grade>();
		Cursor cursor = database.query(DatabaseHelper.gradesTable, allColumns, 
				DatabaseHelper.colGradeTypeID + " = " + type, null, null, null, DatabaseHelper.colGradeDate);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			courses.add(cursorToGrade(cursor));
			cursor.moveToNext();
		}
		cursor.close();
		return courses;

	}

	/**
	 * This method returns the Grade object that a passed Cursor object points
	 * to.
	 * 
	 * @author Byron Alleman
	 * @param cursor
	 * @return The Grade object that the Cursor points to
	 */
	private Grade cursorToGrade(Cursor cursor) {
		Grade grade = new Grade();
		grade.setId(cursor.getInt(0));
		grade.setAssignment_type_id(cursor.getInt(1));
		grade.setCourse_id(cursor.getInt(2));
		grade.setMax_grade(cursor.getFloat(3));
		grade.setName(cursor.getString(4));
		grade.setGrade(cursor.getFloat(5));
		grade.setDate(cursor.getString(6));

		return grade;
	}
}
