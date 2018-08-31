package jaebee2002.naver.com.myproject;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String ROOT_DIR = "/data/data/jaebee2002.naver.com.myproject/databases/";  //로컬db 저장
    private static final String DATABASE_NAME = "wheregodb.db"; //로컬db명
    private static final int SCHEMA_VERSION = 8; //로컬db 버전

    public DatabaseHelper(Context context)    {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        setDB(context); // setDB에 context 부여
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public static void setDB(Context ctx) {
        File folder = new File(ROOT_DIR);
        if(folder.exists()) {
        } else {
            folder.mkdirs();
        }
        AssetManager assetManager = ctx.getResources().getAssets(); //ctx가 없으면 assets폴더를 찾지 못한다.
        File outfile = new File(ROOT_DIR+"wheregodb.db");
        InputStream is = null;
        FileOutputStream fo = null;
        long filesize = 0;
        try {
            is = assetManager.open("wheregodb.db", AssetManager.ACCESS_BUFFER);
            filesize = is.available();
            byte[] tempdata = new byte[(int) filesize];
            is.read(tempdata);
            is.close();
            outfile.createNewFile();
            fo = new FileOutputStream(outfile);
            fo.write(tempdata);
            fo.close();
//            if (outfile.length() <= 0) {
//                byte[] tempdata = new byte[(int) filesize];
//                is.read(tempdata);
//                is.close();
//                outfile.createNewFile();
//                fo = new FileOutputStream(outfile);
//                fo.write(tempdata);
//                fo.close();
//            } else {
//
//            }
        } catch (IOException e) {
        }
    }
}
