package com.example.taskpivot.adapter

import Task
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.modules.EditTaskScreen

class TaskAdapter(private var tasks: List<Task>, private val activity: AppCompatActivity) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


    fun updateTasks(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }


    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val titleTextView: TextView = itemView.findViewById(R.id.task_title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.task_description)

        init {
            // Set click listener for the itemView
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val task = tasks[position]
                val intent = Intent(activity, EditTaskScreen::class.java)
                intent.putExtra("taskId", task.id)
                activity.startActivity(intent)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_task_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = tasks[position]
        holder.titleTextView.text = currentTask.taskTitle
        holder.descriptionTextView.text = currentTask.taskDescription

    }

    override fun getItemCount() = tasks.size
}
