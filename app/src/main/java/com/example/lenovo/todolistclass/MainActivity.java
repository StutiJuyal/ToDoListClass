package com.example.lenovo.todolistclass;

import android.arch.persistence.room.Room;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    List<Expense> expenses = new ArrayList<>();
    ExpenseAdapter adapter;
    public static final int ADD_EXPENSE_REQUEST_CODE = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.List_View);
        expenseOpenHelper openHelper = expenseOpenHelper.getInstance(getApplicationContext());
        SQLiteDatabase database = openHelper.getReadableDatabase();
        int amountGreaterThan = 0;
        String[] selectionArg = {amountGreaterThan + "",};
        String[] selectioncolumn = {Contract.Expense.Column_id, Contract.Expense.Column_Name, Contract.Expense.Column_Amount};
        Cursor cursor = database.query(Contract.Expense.Table_Name, selectioncolumn, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(Contract.Expense.Column_Name));
            int amount = cursor.getInt(cursor.getColumnIndex(Contract.Expense.Column_Name));
            long id = cursor.getLong(cursor.getColumnIndex(Contract.Expense.Column_Name));
            Expense expense = new Expense(name, amount);
            expense.setId(id);
            expenses.add(expense);
        }

       /* ToDoDatabase toDoDatabase= Room.databaseBuilder(getApplicationContext(),ToDoDatabase.class,"Todo_db").allowMainThreadQueries().build();
        toDoDao=toDoDatabase.getToDoDao();
        expenses=toDoDao.getExpenses();
        */
        adapter = new ExpenseAdapter(this, expenses);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(this);
        View view = new View(this);
        Intent intent1=getIntent();
        onFetchIntent(intent1);
    }
    public void onFetchIntent(Intent data)
    {
        String name=data.getStringExtra("Title");
        String amountString=data.getStringExtra("Amount");
        int amount=Integer.parseInt(amountString);
        Expense expense=new Expense(name,amount);
        expenseOpenHelper openHelper = expenseOpenHelper.getInstance(this);
        SQLiteDatabase database = openHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.Expense.Column_Name, expense.getTitle());
        contentValues.put(Contract.Expense.Column_Amount, expense.getAmount());

        long id = database.insert(Contract.Expense.Table_Name, null, contentValues);
        if (id > -1L) {
            expense.setId(id);
            expenses.add(expense);
            adapter.notifyDataSetChanged();
        }


    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        final Expense expense = expenses.get(i);
        final int position = i;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Delete?");
        builder.setCancelable(false);
        builder.setMessage("Do you want to Delete " + expense.getTitle() + " ?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                expenseOpenHelper expenseList = expenseOpenHelper.getInstance(getApplicationContext());
                SQLiteDatabase database = expenseList.getWritableDatabase();
                long id = expense.getId();
                String[] selectionArg = {id + ""};
                database.delete(Contract.Expense.Table_Name, Contract.Expense.Column_id + "=?", selectionArg);
                expenses.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Expense " + expense.getTitle() + " Deleted!!", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //TODO
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.addExpenseAction) {
            Intent intent = new Intent(this, AddExpenseActivity.class);
            startActivityForResult(intent, ADD_EXPENSE_REQUEST_CODE);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_EXPENSE_REQUEST_CODE) {
            if (resultCode == AddExpenseActivity.ADD_RESULT_CODE && data!=null) {
                if (resultCode == AddExpenseActivity.ADD_RESULT_CODE) {
                    String title = data.getStringExtra(AddExpenseActivity.Title);
                    String amountString = data.getStringExtra(AddExpenseActivity.Amount);
                    int amount = Integer.parseInt(amountString);
                    Expense expense = new Expense(title, amount);

                    expenseOpenHelper openHelper = expenseOpenHelper.getInstance(this);
                    SQLiteDatabase database = openHelper.getWritableDatabase();

                    ContentValues contentValues = new ContentValues();
                    contentValues.put(Contract.Expense.Column_Name, expense.getTitle());
                    contentValues.put(Contract.Expense.Column_Amount, expense.getAmount());

                    long id = database.insert(Contract.Expense.Table_Name, null, contentValues);
                    if (id > -1L) {
                        expense.setId(id);

                        expenses.add(expense);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

        }
    }
}