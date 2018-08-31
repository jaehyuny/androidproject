package jaebee2002.naver.com.myproject;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class AreaSearchCursorAdapter extends SimpleCursorAdapter {
    Cursor cursor;
    Context context;
    int layout;
    LayoutInflater inflater;

    TextView row_id;
    TextView rowid;
    TextView rowname;
    TextView rowage;

    public AreaSearchCursorAdapter(Context context, int layout, Cursor cursor, String[] columns, int[] to) {
        super(context, layout, cursor, columns, to);
        this.cursor = cursor;
        this.layout = layout;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View newView (Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(layout, null);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        row_id = view.findViewById(R.id.row_id);
        rowid = view.findViewById(R.id.rowid);
        rowname = view.findViewById(R.id.rowname);
        rowage = view.findViewById(R.id.rowage);

        row_id.setText(cursor.getString(2).toString());
        rowid.setText(cursor.getString(3).toString());
        rowname.setText(cursor.getFloat(4)+"시간");
        rowage.setText(cursor.getInt(5)+"원");
    }
}
