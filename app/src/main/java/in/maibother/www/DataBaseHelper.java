package in.maibother.www;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_tb";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "ROLL_CODE";
    public static final String COL_5 = "mHINDI";
    public static final String COL_6 = "mENGLISH";
    public static final String COL_7 = "mMATH";
    public static final String COL_8 = "mCOMPUTER";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " +TABLE_NAME+" ( ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,ROLL_CODE INTEGER,mHINDI INTEGER,mENGLISH INTEGER,mMATH INTEGER,mCOMPUTER INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(sqLiteDatabase);


    }

    public boolean insetData(String name, String surname, String rollCode, String Hindi, String English, String math, String computer) {


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, rollCode);
        contentValues.put(COL_5, Hindi);
        contentValues.put(COL_6, English);
        contentValues.put(COL_7, math);
        contentValues.put(COL_8, computer);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return result != -1;

    }

    public Cursor getallData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;

    }
    public boolean updateData(String id,String name, String surname, String rollCode, String Hindi, String English, String math, String computer){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, rollCode);
        contentValues.put(COL_5, Hindi);
        contentValues.put(COL_6, English);
        contentValues.put(COL_7, math);
        contentValues.put(COL_8, computer);
        sqLiteDatabase.update(TABLE_NAME,contentValues,"ID = ?",new String[]{ id });
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,"ID = ?",new String[]{id});

    }
    public Integer deleteDataAll(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,null,null);

    }
}
