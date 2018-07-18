package com.example.lenovo.todolistclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddExpenseActivity extends AppCompatActivity {

    public static final String Title="Title";
    public static final String Amount="Amount";
    public static final int ADD_RESULT_CODE=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
    }
    public void saveExpense(View view)
    {
        EditText titleEditText=findViewById(R.id.NameEditText);
        EditText amountEditText=findViewById(R.id.AmountEditText);
        String name=titleEditText.getText().toString();
        String amount=amountEditText.getText().toString();
        Intent intent=new Intent();
        intent.putExtra(Title,name);
        intent.putExtra(Amount,amount);
        setResult(ADD_RESULT_CODE);
        finish();
    }

}
