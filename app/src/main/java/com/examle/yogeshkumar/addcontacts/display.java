package com.examle.yogeshkumar.addcontacts;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class display extends AppCompatActivity {
    ListView listView;
    Dbhelper dbhelper;
    //ArrayList contactlist;
    Cursor cursor;

    myCursorAdapter myCursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        listView=(ListView)findViewById(R.id.listview);
        dbhelper=new Dbhelper(this,null,null,1);
cursor=dbhelper.showcontacts();
myCursorAdapter=new myCursorAdapter(this,cursor);
listView.setAdapter(myCursorAdapter);
//        contactlist=dbhelper.getallcontacts();
//
////        for(Contacts c:contactlist){
////            n=c.getName();
////        }
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,contactlist);
//        listView.setAdapter(arrayAdapter);

        handleIntent(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {

        setIntent(intent);
        handleIntent(intent);
    }
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }
private void doMySearch(String q){
cursor=dbhelper.search(q);
myCursorAdapter=new myCursorAdapter(this,cursor);
    listView.setAdapter(myCursorAdapter);
}


}



