package com.processfast.csce492;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CoursesDataSource {
	// Database fields
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;

	private String[] allColumns = { DatabaseHelper.colCourseID,
			DatabaseHelper.colDept, DatabaseHelper.colNumber,
			DatabaseHelper.colTeacher, DatabaseHelper.colHours,
			DatabaseHelper.colStyle, DatabaseHelper.colA,
			DatabaseHelper.colA_minus, DatabaseHelper.colB_plus,
			DatabaseHelper.colB, DatabaseHelper.colB_minus,
			DatabaseHelper.colC_plus, DatabaseHelper.colC,
			DatabaseHelper.colC_minus, DatabaseHelper.colD_plus,
			DatabaseHelper.colD, DatabaseHelper.colD_minus };

	public CoursesDataSource(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	/**
	 * The method adds a course to the database.
	 * 
	 * @author Byron Alleman
	 * @param dept
	 * @param number
	 * @param teacher
	 *            can be null
	 * @param hours
	 * @param style
	 * @param a
	 * @param a_minus
	 *            can be null
	 * @param b_plus
	 *            can be null
	 * @param b
	 * @param b_minus
	 *            can be null
	 * @param c_plus
	 *            can be null
	 * @param c
	 * @param c_minus
	 *            can be null
	 * @param d_plus
	 *            can be null
	 * @param d
	 * @param d_minus
	 *            can be null
	 * @return A cursor to the newly created Course
	 */
	public Course createCourse(String dept, String number, String teacher,
			int hours, int style, int a, int a_minus, int b_plus, int b,
			int b_minus, int c_plus, int c, int c_minus, int d_plus, int d,
			int d_minus) {
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.colDept, dept);
		values.put(DatabaseHelper.colNumber, number);
		values.put(DatabaseHelper.colTeacher, teacher);
		values.put(DatabaseHelper.colHours, hours);
		values.put(DatabaseHelper.colStyle, style);
		values.put(DatabaseHelper.colA, a);
		values.put(DatabaseHelper.colA_minus, a_minus);
		values.put(DatabaseHelper.colB_plus, b_plus);
		values.put(DatabaseHelper.colB, b);
		values.put(DatabaseHelper.colB_minus, b_minus);
		values.put(DatabaseHelper.colC_plus, c_plus);
		values.put(DatabaseHelper.colC, c);
		values.put(DatabaseHelper.colC_minus, c_minus);
		values.put(DatabaseHelper.colD_plus, d_plus);
		values.put(DatabaseHelper.colD, d);
		values.put(DatabaseHelper.colD_minus, d_minus);

		long insertId = database.insert(DatabaseHelper.coursesTable, null,
				values);
		Log.i("Course", "Course # " + insertId + " added to database.");
		Cursor cursor = database.query(DatabaseHelper.coursesTable, allColumns,
				DatabaseHelper.colCourseID + " = " + insertId, null, null,
				null, null);

		cursor.moveToFirst();
		return cursorToCourse(cursor);
	}
	public Course addCourseToDatabase(Course c){
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.colDept, c.getDept());
		values.put(DatabaseHelper.colNumber, c.getNumber());
		values.put(DatabaseHelper.colTeacher, c.getTeacher());
		values.put(DatabaseHelper.colHours, c.getHours());
		values.put(DatabaseHelper.colStyle, c.getStyle());
		values.put(DatabaseHelper.colA, c.getScale().getA());
		values.put(DatabaseHelper.colA_minus, c.getScale().getA_minus());
		values.put(DatabaseHelper.colB_plus, c.getScale().getB_plus());
		values.put(DatabaseHelper.colB, c.getScale().getB());
		values.put(DatabaseHelper.colB_minus, c.getScale().getB_minus());
		values.put(DatabaseHelper.colC_plus, c.getScale().getC_plus());
		values.put(DatabaseHelper.colC, c.getScale().getC());
		values.put(DatabaseHelper.colC_minus, c.getScale().getC_minus());
		values.put(DatabaseHelper.colD_plus, c.getScale().getD_plus());
		values.put(DatabaseHelper.colD, c.getScale().getD());
		values.put(DatabaseHelper.colD_minus, c.getScale().getD_minus());
		
		long insertId = database.insert(DatabaseHelper.coursesTable, null,
				values);
		
		Log.i("Course", "Course # " + insertId + " added to database.");
		Cursor cursor = database.query(DatabaseHelper.coursesTable, allColumns,
				DatabaseHelper.colCourseID + " = " + insertId, null, null,
				null, null);

		cursor.moveToFirst();
		return cursorToCourse(cursor);
	}

	/**
	 * Deletes a course from the database
	 * 
	 * @author Byron Alleman
	 * @param course
	 *            The course to be deleted
	 */
	public void deleteCourse(Course course) {
		int id = course.getId();
		Log.i("Course", "Course # " + id + " is deleted.");
		database.delete(DatabaseHelper.coursesTable, DatabaseHelper.colCourseID
				+ " = " + id, null);
	}
	
	/**
	 * Deletes a course from the database by the course's id number
	 * 
	 * @author Byron Alleman
	 * @param id
	 *            The id of the course to be deleted
	 */
	public void deleteCourse(int id) {
		Log.i("Course", "Course # " + id + " is deleted.");
		database.delete(DatabaseHelper.coursesTable, DatabaseHelper.colCourseID
				+ " = " + id, null);
	}

	/**
	 * @author Byron Alleman
	 * @return List of all courses
	 */
	public List<Course> getAllCourses() {
		List<Course> courses = new ArrayList<Course>();
		Cursor cursor = database.query(DatabaseHelper.coursesTable, allColumns,
				null, null, null, null,
				DatabaseHelper.colDept + ", " + DatabaseHelper.colNumber);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			courses.add(cursorToCourse(cursor));
			cursor.moveToNext();
		}
		cursor.close();
		return courses;

	}

	/**
	 * @param courseID the id of the course being returned
	 * @return the course associated with the id
	 */
	public Course getCourseByID(int courseID){
		Course course = new Course();
		Cursor cursor = database.query(DatabaseHelper.coursesTable, allColumns,
				DatabaseHelper.colCourseID + "=?", new String[]{courseID+""}, null, null, null);
		cursor.moveToFirst();
		course = cursorToCourse(cursor);
		
		return course;
	}
	/**
	 * This method returns the Course object that a passed Cursor object points
	 * to.
	 * 
	 * @author Byron Alleman
	 * @param cursor
	 * @return The Course object that the Cursor points to
	 */
	private Course cursorToCourse(Cursor cursor) {
		Course course = new Course();
		course.setId(cursor.getInt(0));
		course.setDept(cursor.getString(1));
		course.setNumber(cursor.getString(2));
		course.setTeacher(cursor.getString(3));
		course.setHours(cursor.getInt(4));
		course.setStyle(cursor.getInt(5));
		course.getScale().setA(cursor.getInt(6));
		course.getScale().setA_minus(cursor.getInt(7));
		course.getScale().setB_plus(cursor.getInt(8));
		course.getScale().setB(cursor.getInt(9));
		course.getScale().setB_minus(cursor.getInt(10));
		course.getScale().setC_plus(cursor.getInt(11));
		course.getScale().setC(cursor.getInt(12));
		course.getScale().setC_minus(cursor.getInt(13));
		course.getScale().setD_plus(cursor.getInt(14));
		course.getScale().setD(cursor.getInt(15));
		course.getScale().setD_minus(cursor.getInt(16));

		return course;
	}
}
