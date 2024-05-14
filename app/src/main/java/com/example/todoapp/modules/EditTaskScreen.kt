package com.example.todoapp.modules

import Task
import TaskViewModel
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityEditBinding
import com.example.todoapp.databinding.ActivityHomeBinding

class EditTaskScreen : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    private var taskId : Int = -1
    private lateinit var taskViewModel: TaskViewModel
    private var confirmationDialog: Dialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskId = intent.getIntExtra("taskId",-1)

        taskViewModel = TaskViewModel(application)

        val task = taskViewModel.getTaskById(taskId)
        if (task != null) {
            // Display task details
            val taskTitle = task.taskTitle
            binding.taskTitle.text = Editable.Factory.getInstance().newEditable(taskTitle)
            val taskDescription = task.taskDescription
            binding.taskDescription.text = Editable.Factory.getInstance().newEditable(taskDescription)
        }

        binding.doneBtn.setOnClickListener {
            saveEditedTask()
        }

        binding.deleteBtn.setOnClickListener {
            showConformation()
        }

    }

    private fun saveEditedTask(){

        val editedTitle = binding.taskTitle.text.toString()
        val editedDescription = binding.taskDescription.text.toString()

        val editedTask = Task(
            id = taskId,
            taskTitle = editedTitle,
            taskDescription = editedDescription,
        )

        val updatedRows = taskViewModel.updateTask(editedTask)

        if (updatedRows > 0) {
            Toast.makeText(this, "Task updated successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeScreen::class.java)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Failed to update task", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        confirmationDialog?.dismiss()
    }

    private fun showConformation() {
        val dialog = Dialog(this, R.style.CustomDialogTheme)
        confirmationDialog = dialog
        dialog.setContentView(R.layout.delete_confromation_popup)

        val cancel_btn : Button = dialog.findViewById(R.id.cancel_btn)

        cancel_btn.setOnClickListener{
            dialog.hide()
        }

        val ok_btn : Button = dialog.findViewById(R.id.ok_btn)

        ok_btn.setOnClickListener{
            taskViewModel.deleteTask(taskId)
            val intent = Intent(this, HomeScreen::class.java)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            startActivity(intent)
            finish()
        }


        dialog.show()
    }
}