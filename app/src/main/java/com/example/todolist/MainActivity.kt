package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/* This is the Screen that contains several views.
* */
class MainActivity : AppCompatActivity() {
    /* Put anything we want to run immediately on startup within the onCreate() function.
    * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}