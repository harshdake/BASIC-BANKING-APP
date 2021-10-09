package com.example.basicbankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(8735226109,'Harsh',963.23,'harsh24@gmail.com','XXXXXXX8541','JKL5459821')");
        db.execSQL("insert into user_table values(7569234561,'Aniket',895.18,'aniket@12gmail.com','XXXXXXX1452','ABC7894562')");
        db.execSQL("insert into user_table values(9632514896,'Swapnil',976.14,'swapy@4gmail.com','XXXXXXX1241','PLK7895415')");
        db.execSQL("insert into user_table values(7853621490,'Sushil',860.26,'sush7@gmail.com','XXXXXXX8442','ASD8546321')");
        db.execSQL("insert into user_table values(9853621045,'Ajay',967.01,'ajay@13gmail.com','XXXXXXX6565','CVB4625103')");
        db.execSQL("insert into user_table values(9757671230,'Mithilesh',842.08,'mith18@gmail.com','XXXXXXX2136','RTY9856552')");
        db.execSQL("insert into user_table values(7579632104,'Somesh',985.25,'somesh17@gmail.com','XXXXXXX2226','SBJ2854691')");
        db.execSQL("insert into user_table values(8985632410,'Kabir',975.18,'kabir19@gmail.com','XXXXXXX6234','PKJ8974562')");
        db.execSQL("insert into user_table values(7458965201,'Aditya',836.12,'adi@96gmail.com','XXXXXXX9521','CBN8246210')");
        db.execSQL("insert into user_table values(7709856321,'Pranay',885.21,'Pranay@24gmail.com','XXXXXXX8562','AVM9457203')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
