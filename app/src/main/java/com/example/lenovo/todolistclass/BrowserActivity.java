package com.example.lenovo.todolistclass;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class BrowserActivity extends AppCompatActivity {

    int year,month,day,date,hour,min;
    EditText nameEditText;
    EditText amountEditText;
    private static final String TitleName="Title";
    private static final String AmountName="Amount";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        nameEditText=findViewById(R.id.TitleTextView);
        amountEditText=findViewById(R.id.Amount);

    }
    public void SendToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.putExtra(TitleName, nameEditText.getText().toString());
        intent.putExtra(AmountName, amountEditText.getText().toString());
        startActivity(intent);
    }
}
