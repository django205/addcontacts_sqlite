package com.examle.yogeshkumar.addcontacts;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.CursorAnchorInfo;
import android.widget.CursorAdapter;
import android.widget.TextView;


/**
 * Created by Yogesh Kumar on 3/24/2017.
 */

public class myCursorAdapter extends CursorAdapter {
    public myCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,  false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView name=(TextView)view.findViewById(R.id.name);
        TextView number=(TextView)view.findViewById(R.id.number);
        TextView email=(TextView)view.findViewById(R.id.email);
        String names= cursor.getString(cursor.getColumnIndex("name"));
        String numbers= cursor.getString(cursor.getColumnIndex("number"));
        String emails= cursor.getString(cursor.getColumnIndex("email"));
        name.setText(names);
        number.setText(numbers);
        email.setText(emails);

    }
}
