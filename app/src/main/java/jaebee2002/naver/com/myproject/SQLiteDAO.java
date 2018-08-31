package jaebee2002.naver.com.myproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDAO extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "wherego4.db";
    private static final int DATABASE_VERSION = 1;
    private Context mContext;

    public SQLiteDAO(Context context){
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
        mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("create table area(_id INTEGER PRIMARY KEY AUTOINCREMENT,");
        sb.append("start varchar, arrive varchar, vehicle varchar, time float, money integer)");
        db.execSQL(sb.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists cparea");
        db.execSQL("create table cparea as select * from area");
        db.execSQL("drop table if exists area");
        onCreate(db);
        dataKeepMethod(db);

    }
    private void dataKeepMethod(SQLiteDatabase db){
        String sql = "select * from cparea";
        Cursor cursor = db.rawQuery(sql, null);
        StringBuilder insertsql;
        while(cursor.moveToNext()){
            insertsql = new StringBuilder();
            insertsql.append("insert into area(");
            insertsql.append("start,arrive,vehicle,time,money");
            insertsql.append(")values(");
            insertsql.append(" '"+cursor.getString(1)+"', '"+cursor.getString(2)+"', '"+cursor.getString(3)+"', "+cursor.getFloat(4)+", "+cursor.getInt(5)+" ");
            insertsql.append(")");
            db.execSQL(insertsql.toString());
        }
    }
}
