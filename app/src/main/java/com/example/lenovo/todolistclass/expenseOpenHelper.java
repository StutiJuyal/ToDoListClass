package com.example.lenovo.todolistclass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class expenseOpenHelper extends SQLiteOpenHelper {
    private static String databaseName="expensesDB";
    private static int version=1;
    private static expenseOpenHelper instance;

    public expenseOpenHelper(Context context) {
        super(context,databaseName,null,version);
    }
    public static expenseOpenHelper getInstance(Context context)
    {
        if(instance==null)
        {
            instance=new expenseOpenHelper(context.getApplicationContext());
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
      String expensesSQL="CREATE TABLE "+Contract.Expense.Table_Name+"("+Contract.Expense.Column_id+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+Contract.Expense.Column_Name+" TEXT ,"+Contract.Expense.Column_Amount+" Integer )";
      db.execSQL(expensesSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
