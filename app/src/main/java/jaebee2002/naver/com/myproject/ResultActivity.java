package jaebee2002.naver.com.myproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    ListView resultlist;
    Context context;
    TextView starttxt;
    Intent intent;
    String arrive;
    String time;
    String money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultlist = findViewById(R.id.resultlist);
        starttxt = findViewById(R.id.starttxt);
        context = this;
        intent = getIntent();
        starttxt.setText(intent.getStringExtra("start"));
//        SQLiteDAO obj = new SQLiteDAO(ResultActivity.this);
//        SQLiteDatabase db = obj.getReadableDatabase();
        DatabaseHelper obj = new DatabaseHelper(ResultActivity.this);
        SQLiteDatabase db = obj.getReadableDatabase();
        if(intent.getStringExtra("arrive").equals("상관없음")){
            arrive = "";
        }else {
            arrive = "and arrive= '"+intent.getStringExtra("arrive")+"' ";
        }
        if(intent.getStringExtra("time").equals("상관없음")){
            time = "";
        }else {
            time = "and time<= '"+intent.getStringExtra("time")+"' ";
        }
        if(intent.getStringExtra("money").equals("상관없음")){
            money = "";
        }else {
            money = "and money<= '"+intent.getStringExtra("money")+"' ";
        }
        String sql = "select * from area where start="+" '"+starttxt.getText().toString()+"' "+ arrive + time + money + "order by arrive";
        Cursor cursor = db.rawQuery(sql, null);

//        String[] columns = new String[]{"arrive","vehicle","time","money"};
//        int[] to = new int[]{R.id.row_id,R.id.rowid,R.id.rowname,R.id.rowage};
//
//        CursorAdapter adapter = new AreaSearchCursorAdapter(getApplicationContext(),R.layout.activity_search_row,cursor,columns,to);
        int count = cursor.getCount();   // db에 저장된 행 개수를 읽어온다
        String[] columns = new String[count];   // 저장된 행 개수만큼의 배열을 생성
        for (int i = 0; i < count; i++) {
            cursor.moveToNext();
            String arrive = cursor.getString(2).toString();
            String vehicle = cursor.getString(3).toString();
            Integer h = cursor.getInt(4);
            String hour = h+"시간  ";
            if(h == 0){
                hour = "";
            }
            Double d = 100.1;
            Double m = (cursor.getFloat(4) - h) * d;
            String minute = String.format("%.0f", m)+"분 \t ";
            if(m == 0){
                minute = "";
            }
            String money = cursor.getInt(5)+"원";
            columns[i] = arrive +" \t "+ vehicle +" \t "+ hour + minute + money ;
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, columns);
        resultlist.setAdapter(adapter);
//        resultlist.setOnItemClickListener(itemlistener);
    }
}
