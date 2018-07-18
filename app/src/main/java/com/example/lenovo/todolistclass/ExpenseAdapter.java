package com.example.lenovo.todolistclass;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter extends ArrayAdapter {

    List<Expense> items;
    LayoutInflater inflater;
    int inflaterCount=0;
    public ExpenseAdapter(@NonNull Context context, List<Expense>items) {
        super(context,0,items);
        inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.items=items;
    }
    @Override
    public int getCount(){return items.size(); }
    @Override
    public long getItemId(int position){ return position;}
    @Override
    public View getView(int position, @Nullable View ConvertView,@NonNull ViewGroup parent)
    {
        View output=ConvertView;
        if(output==null)
        {
            inflaterCount++;
            Log.d("ExpenseAdapter","Inflater Count="+inflaterCount);
            output=inflater.inflate(R.layout.expense_row_layout,parent,false);
            TextView nameTextView=output.findViewById(R.id.name);
            TextView amountTextView=output.findViewById(R.id.amount);
            ExpenseView expenseView=new ExpenseView();
            expenseView.nameTextView=nameTextView;
            expenseView.amountTextView=amountTextView;
            output.setTag(expenseView);
        }
        ExpenseView expenseView=(ExpenseView)output.getTag();
        Expense expense=items.get(position);
        expenseView.nameTextView.setText(expense.getTitle());
        expenseView.amountTextView.setText(expense.getAmount()+"");
        return output;
    }

}
