package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Pass empty list
        todoAdapter = TodoAdapter(mutableListOf())
        rvTodoItems.adapter = todoAdapter

        // Define how items are arranged
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        // Define what should happen when buttons are click
        btnAddTodo.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                // Clear text for the next todo item to be entered
                etTodoTitle.text.clear()
            }
        }

        btnDeleteDoneTodo.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}