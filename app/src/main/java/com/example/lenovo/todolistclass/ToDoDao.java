package com.example.lenovo.todolistclass;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ToDoDao  {

    @Insert
    void addExpenses(Expense expense);

    @Delete
    void deleteExpense(Expense expense);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateExpense(Expense expense);

    @Insert
    void addTwoExpenses(Expense expense1, Expense expense2);

    @Insert
    void addExpenses(Expense[] expenses);

    @Query("select * from expense")
    List<Expense> getExpenses();
}
