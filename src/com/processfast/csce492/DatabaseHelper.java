package com.processfast.csce492;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	static final String dbName = "passOrFailDB";
	private static final int DATABASE_VERSION = 1;
	
	static final String coursesTable = "Courses";
	static final String colCourseID = "_id";
	static final String colDept = "dept";
	static final String colNumber = "number";
	static final String colTeacher = "teacher";
	static final String colHours = "hours";
	static final String colStyle = "style";
	static final String colA = "A";
	static final String colA_minus = "A_minus";
	static final String colB_plus = "B_plus";
	static final String colB = "B";
	static final String colB_minus = "B_minus";
	static final String colC_plus = "C_plus";
	static final String colC = "C";
	static final String colC_minus = "C_minus";
	static final String colD_plus = "D_plus";
	static final String colD = "D";
	static final String colD_minus = "D_minus";
	
	static final String assignmentTypesTable = "AssignmentTypes";
	static final String colTypeID = "_id";
	static final String colTypeCourseID = "course_id";
	static final String colWeight = "weight";
	static final String colName = "name";
	
	static final String gradesTable = "Grades";
	static final String colGradeID = "_id";
	static final String colGradeTypeID = "assignment_type_id";
	static final String colGradeCourseID = "course_id";
	static final String colMaxGrade = "max_grade";
	static final String colGrade = "grade";
	static final String colGradeName = "grade_name";
	static final String colGradeDate = "grade_date";
	
	private static final String COURSE_TABLE_CREATE = 
			"CREATE TABLE " + coursesTable + 
			" (" +
			colCourseID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			colDept + " TEXT NOT NULL, " +
			colNumber + " T NOT NULL, " +
			colTeacher + " TEXT, " +
			colHours + " INTEGER NOT NULL, " + 
			colStyle + " INTEGER NOT NULL, " +
			colA + " INTEGER NOT NULL," +
			colA_minus + " INTEGER, " +
			colB_plus + " INTEGER, " +
			colB + " INTEGER NOT NULL, " +
			colB_minus + " INTEGER, " +
			colC_plus + " INTEGER, " +
			colC + " INTEGER NOT NULL," +
			colC_minus + " INTEGER, " +
			colD_plus + " INTEGER, " +
			colD + " INTEGER NOT NULL, " +
			colD_minus + " INTEGER " +		
			");";
	
	
	private static final String ASSIGNMENT_TYPES_TABLE_CREATE = 
			"CREATE TABLE " + assignmentTypesTable + 
			" (" +
			colTypeID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			colTypeCourseID + " INTEGER NOT NULL," +
			colWeight + " INTEGER NOT NULL, " +
			colName + " TEXT NOT NULL" +
			");";
	
	private static final String GRADES_TABLE_CREATE = 
			"CREATE TABLE " + gradesTable + 
			" (" +
			colGradeID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			colGradeTypeID + " INTEGER NOT NULL," +
			colGradeCourseID + " INTEGER NOT NULL," +
			colMaxGrade + " FLOAT NOT NULL, " +
			colGradeName + " TEXT NOT NULL," +
			colGrade + " FLOAT NOT NULL," + 
			colGradeDate + " DATE DEFAULT CURRENT_TIMESTAMP" +
			");";

	public DatabaseHelper(Context context) {
		super(context, dbName, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Create Courses Table
		db.execSQL(COURSE_TABLE_CREATE);
		
		//  Create Assignment Types Table
		db.execSQL(ASSIGNMENT_TYPES_TABLE_CREATE);
		// TODO Create Grades Table
		db.execSQL(GRADES_TABLE_CREATE);
				
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DatabaseHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS" + coursesTable);
		db.execSQL("DROP TABLE IF EXISTS" + assignmentTypesTable);
		db.execSQL("DROP TABLE IF EXISTS" + gradesTable);
		onCreate(db);
		
	}
}
