package com.example.lenovo.todolistclass;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Calendar;

@Entity
public class Expense {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String Title;
    private int Amount;
    public Expense(String Title,int Amount)
    {
        this.Title=Title;
        this.Amount=Amount;
    }
    public long getId(){return id;}
    public void setId(long id){this.id=id;}
    public String getTitle(){return Title;}
    public void setTitle(String Title){this.Title=Title;}
    public int getAmount(){return Amount;}
    public void setAmount(int Amount){this.Amount=Amount;}
}
