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
import android.widget.SimpleCursorAdapter;

public class StartAreaActivity extends AppCompatActivity {
    ListView arealist;
    Context context;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_area);
        arealist = findViewById(R.id.arealist);
        context = this;
        intent = getIntent();
//        SQLiteDAO obj = new SQLiteDAO(StartAreaActivity.this);
//        SQLiteDatabase db = obj.getReadableDatabase();
        DatabaseHelper obj = new DatabaseHelper(StartAreaActivity.this);
        SQLiteDatabase db = obj.getReadableDatabase();
        String sql = "select distinct start from area order by start";
        Cursor cursor = db.rawQuery(sql, null);

        int count = cursor.getCount();
        String[] columns = new String[count];
        for (int i = 0; i < count; i++) {
            cursor.moveToNext();
            String start = cursor.getString(0).toString();
            columns[i] = start ;
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, columns);
        arealist.setAdapter(adapter);
        arealist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = arealist.getItemAtPosition(position).toString();
                intent.putExtra("start",selected);
                setResult(101,intent);
                finish();
            }
        });
    }
}
