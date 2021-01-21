package com.example.rambazamba;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MyCursorAdapter extends CursorAdapter {
    private final LayoutInflater layoutInflater;
    private DataBaseHelper myDbHelper;

    public MyCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return layoutInflater.inflate(R.layout.activity_recycler_items, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView idTextView = view.findViewById(R.id.id);
        TextView activityTxtView = view.findViewById(R.id.activityTxtView);

        int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper._ID));
        idTextView.setText(String.valueOf(id));

        String activityTxt = cursor.getString(cursor.getColumnIndex(DataBaseHelper.ACTIVITY));
        if (!activityTxt.equals(DataBaseHelper.ACTIVITY)) {
            activityTxtView.setText(activityTxt);
        } else {
            Toast.makeText(context, activityTxt + " is already in Favourites !", Toast.LENGTH_LONG).show();
        }


    }
}
