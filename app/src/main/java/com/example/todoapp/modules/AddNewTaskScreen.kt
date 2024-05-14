package com.example.todoapp.modules

import Task
import TaskViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityAddNoteBinding
import com.example.todoapp.databinding.ActivityOngoingscreenBinding

class AddNewTaskScreen : AppCompatActivity() {

    private lateinit var binding : ActivityAddNoteBinding
    private var taskDescription : String = ""
    private var taskTitle : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.doneBtn.setOnClickListener {
            taskTitle = binding.taskTitle.text.toString()
            taskDescription = binding.taskDescription.text.toString()
            saveTask()
        }
    }

    private fun saveTask() {

        val taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        val taskcount = taskViewModel.getAllTasks().size + 1

        val task = Task(
            id = taskcount,
            taskTitle = taskTitle,
            taskDescription = taskDescription,
        )

        val result = taskViewModel.addTask(task)
        if (result != -1L) {
            Toast.makeText(this, "Task Created Successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }
}