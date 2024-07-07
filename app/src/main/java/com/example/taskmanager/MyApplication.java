package com.example.taskmanager;

import android.app.Application;

import com.example.taskmanager.Tasks;

import java.util.ArrayList;

public class MyApplication extends Application {
    public static ArrayList<Tasks> tasks;

    @Override
    public void onCreate() {
        super.onCreate();
        tasks = new ArrayList<>();

    }
}
