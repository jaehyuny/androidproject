package jaebee2002.naver.com.myproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ArriveAreaActivity extends AppCompatActivity {
    ListView arrivelist;
    Context context;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrive_area);
        arrivelist = findViewById(R.id.arrivelist);
        context = this;
        intent = getIntent();
//        SQLiteDAO obj = new SQLiteDAO(ArriveAreaActivity.this);
//        SQLiteDatabase db = obj.getReadableDatabase();
        DatabaseHelper obj = new DatabaseHelper(ArriveAreaActivity.this);
        SQLiteDatabase db = obj.getReadableDatabase();
        String sql = "select distinct arrive from area order by arrive";
        Cursor cursor = db.rawQuery(sql, null);

        int count = cursor.getCount()+1;   // db에 저장된 행 개수를 읽어온다
        String[] columns = new String[count];   // 저장된 행 개수만큼의 배열을 생성
        columns[0] = "상관없음";
        for (int i = 1; i < count; i++) {
            cursor.moveToNext();
            String arrive = cursor.getString(0).toString();
            columns[i] = arrive ;
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, columns);
        arrivelist.setAdapter(adapter);
        arrivelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = arrivelist.getItemAtPosition(position).toString();
                intent.putExtra("arrive",selected);
                setResult(102,intent);
                finish();
            }
        });
    }
}
