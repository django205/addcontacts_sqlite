package com.examle.yogeshkumar.addcontacts;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText name,number,email;
    Dbhelper dbhelper;
    Button save,show,delete,update;
    Contacts c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.name);
        number=(EditText)findViewById(R.id.number);
        email=(EditText)findViewById(R.id.email);
        save=(Button)findViewById(R.id.add);
        show=(Button)findViewById(R.id.show);
        delete=(Button)findViewById(R.id.delete);
        update=(Button)findViewById(R.id.update);
        dbhelper=new Dbhelper(this,null,null,1);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c=new Contacts(name.getText().toString(),email.getText().toString(),number.getText().toString());
               boolean isinsert= dbhelper.addContacts(c);

                if(isinsert==true){
                    Toast.makeText(getApplicationContext(),"data inserted",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getApplicationContext(),"data not inserted",Toast.LENGTH_LONG).show();

                }
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,display.class);
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=new Contacts(name.getText().toString(),email.getText().toString(),number.getText().toString());
               Integer i= dbhelper.deleteContacts(c.getName());
                if(i==0) Toast.makeText(getApplicationContext(),"not deleted",Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(),"deleted",Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=new Contacts(name.getText().toString(),email.getText().toString(),number.getText().toString());
              boolean isupddated=  dbhelper.updatecontact(c);
                if(isupddated==false)Toast.makeText(getApplicationContext(),"not updated",Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(),"updated",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_display, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
}
