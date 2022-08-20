package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*


/**
 * Contains main logic of the Todo List application.
 */
class TodoAdapter(private val todos: MutableList<Todo>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        // Return using an inflation of the xml layout file
        return TodoViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false))
    }

    /**
     * If an item is 'checked' complete, then we strike through the text
     */
    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        // Note that the 'or' and 'and' are binary operators.
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    /**
     * Add todo to the list when user clicks the button to add
     */
    fun addTodo(todo: Todo) {
        todos.add(todo)

        // Update RecyclerView.Adapter
        notifyItemInserted(todos.size - 1)
    }

    /**
     * Delete Todo items that are marked as done with isChecked = true.
     */
    fun deleteDoneTodos() {
        todos.removeAll { it.isChecked }

        // Update the RecyclerView.Adapter
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        // Note - Called by the RecyclerView to display the data at the specified location
        // See - https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.Adapter?hl=en#onbindviewholder*/
        val currentTodo = todos[position]
        // Configure defaults for new items in todo list
        holder.itemView.apply {
            // Configure the layout properties based on the currentTodo values.
            tvTodoTitle.text = currentTodo.title
            cbDone.isChecked = currentTodo.isChecked
            toggleStrikeThrough(tvTodoTitle, currentTodo.isChecked)

            // Use a listener for the check box. When the "checked" on/off state of the check box
            // changes then we register a callback to change the strike through
            // to the opposite settings (on becomes off and off becomes on).
            cbDone.setOnCheckedChangeListener {
                    _, isChecked -> toggleStrikeThrough(tvTodoTitle, isChecked)
                currentTodo.isChecked = !currentTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}