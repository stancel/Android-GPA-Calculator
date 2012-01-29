import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	static final String dbName = "passOrFailDB";
	
	static final String coursesTable = "Courses";
	static final String colCourseID = "id";
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
	
	static final String AssignmentTypesTable = "AssignmentTypes";
	static final String colTypeID = "id";
	static final String colTypeCourseID = "course_id";
	static final String colWeight = "weight";
	static final String colName = "name";
	
	static final String GradesTable = "Grades";
	static final String colGradeID = "id";
	static final String colGradeTypeID = "assignment_type_id";
	static final String colGradeCourseID = "course_id";
	static final String colMaxGrade = "max_grade";
	static final String colGrade = "grade";
	

	public DatabaseHelper(Context context) {
		super(context, dbName, null, 33);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Create Courses Table
		db.execSQL("CREATE TABLE " + coursesTable + 
				" (" +
				colCourseID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				colDept + " TEXT NOT NULL, " +
				colNumber + " INTEGER NOT NULL, " +
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
				colD_minus + " INTEGER, " +		
				");");
		// TODO Create Assignment Types Table
		
		// TODO Create Grades Table
		
		
		
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
