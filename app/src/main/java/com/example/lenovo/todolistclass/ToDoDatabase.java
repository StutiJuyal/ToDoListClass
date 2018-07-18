package com.example.lenovo.todolistclass;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
@Database(entities = Expense.class,version = 1)
public abstract class ToDoDatabase extends RoomDatabase {
    abstract ToDoDao getToDoDao();
}
