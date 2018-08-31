package jaebee2002.naver.com.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    LinearLayout baselayout;
    InputMethodManager imm;
    EditText start;
    EditText arrive;
    EditText time;
    EditText money;
    Button go;
    Button reset;
    final int RESULT_OK = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 101){
            start.setText(data.getStringExtra("start"));
        }else if(resultCode == 102){
            arrive.setText(data.getStringExtra("arrive"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        baselayout = findViewById(R.id.baselayout);
        start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, StartAreaActivity.class);
                startActivityForResult(intent, RESULT_OK);
            }
        });
        arrive = findViewById(R.id.arrive);
        arrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, ArriveAreaActivity.class);
                startActivityForResult(intent, RESULT_OK);
            }
        });
        time = findViewById(R.id.time);
        time.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(time.getText().toString().equals("상관없음")){
                    time.setText("");
                }
                return false;
            }
        });
        money = findViewById(R.id.money);
        money.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(money.getText().toString().equals("상관없음")){
                    money.setText("");
                }
                return false;
            }
        });
        go = findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(start.getText().toString().equals("")){
                    Toast.makeText(Main2Activity.this, "출발지를 입력하세요", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(Main2Activity.this, ResultActivity.class);
                    intent.putExtra("start", start.getText().toString());
                    intent.putExtra("arrive", arrive.getText().toString());
                    intent.putExtra("time", time.getText().toString());
                    intent.putExtra("money", money.getText().toString());
                    startActivityForResult(intent, RESULT_OK);
                }
            }
        });
        reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setText("");
                arrive.setText("상관없음");
                time.setText("상관없음");
                money.setText("상관없음");
            }
        });
        baselayout.setOnTouchListener(new MyTouchListener());
    }

    private class MyTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            imm.hideSoftInputFromWindow(time.getWindowToken(),0);
            imm.hideSoftInputFromWindow(money.getWindowToken(),0);
            if(time.getText().toString().equals("")){
                time.setText("상관없음");
            }
            if(money.getText().toString().equals("")){
                money.setText("상관없음");
            }
            time.clearFocus();
            money.clearFocus();

            return false;
        }
    }
}
