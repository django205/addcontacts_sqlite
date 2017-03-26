package com.examle.yogeshkumar.addcontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Yogesh Kumar on 3/22/2017.
 */

    public class Dbhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="django.db";
    public static final int DATABASE_VERSION=1;
    public static final String TABLENAME="contacts";
    public static final String NAME="name";
    public static final String EMAIL="email";
    public static final String NUMBER="number";
    public static final String ID="_id";
String name,no,email;
    Contacts contacts;
    public Dbhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int  version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query="create table if not exists "+TABLENAME+" ( _id integer autoincrement,name varchar,number varchar primary key,email varchar);";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
sqLiteDatabase.execSQL("drop table if exists "+ TABLENAME);

        onCreate(sqLiteDatabase);

    }

    public boolean addContacts(Contacts contacts){
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,contacts.getName());
        contentValues.put(NUMBER,contacts.getNumber());
        contentValues.put(EMAIL,contacts.getEmail());
        SQLiteDatabase db=getWritableDatabase();
     long r= db.insert(TABLENAME,null,contentValues);
     if(r==-1) return false;
        else return true;

    }
    public Integer deleteContacts(String contactname){
SQLiteDatabase db=getWritableDatabase();
        //db.execSQL("delete table from contacts where name ="+contactname+";");
        Integer i=db.delete(TABLENAME,NAME+" =?",new String []{ contactname});
       return i;
    }

    public Cursor showcontacts(){
        SQLiteDatabase db=getWritableDatabase();
        String query="select * from contacts where 1";
        Cursor c=db.rawQuery(query,null);
        //c.moveToFirst();

      return c;
    }
//    public ArrayList getallcontacts(){
//
//
//        ArrayList list=new ArrayList();
//        SQLiteDatabase db=getWritableDatabase();
//        String query="select * from contacts where 1";
//        Cursor c=db.rawQuery(query,null);
//        c.moveToFirst();
//        while (c.isAfterLast()==false){
//          name=c.getString(c.getColumnIndex(NAME));
//            //no=c.getString(c.getColumnIndex(NUMBER));
//            //email=c.getString(c.getColumnIndex(EMAIL));
//         list.add(name);
//            //list.add(new Contacts(name,email,no));
//            c.moveToNext();
//        }
//
////        if(c.moveToFirst()){
////            do{
////
////            }while(c.moveToNext());
////        }
//        return list;
//    }

    public boolean updatecontact(Contacts contacts){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,contacts.getName());
        contentValues.put(NUMBER,contacts.getNumber());
        contentValues.put(EMAIL,contacts.getEmail());
        db.update(TABLENAME,contentValues,NAME+" =?",new String []{ contacts.getName()});
        return true;
    }

    public Cursor search(String q){
        SQLiteDatabase db=getWritableDatabase();
        String query="select * from contacts where name like '%"+q+"%'";
        Cursor c=db.rawQuery(query,null);
        return c;
    }
}
