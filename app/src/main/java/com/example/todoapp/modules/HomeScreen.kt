package com.example.todoapp.modules

import Task
import TaskViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskpivot.adapter.TaskAdapter
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityHomeBinding

class HomeScreen : AppCompatActivity(){
    private lateinit var binding: ActivityHomeBinding

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private var tasks: List<Task> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addNewBtn.setOnClickListener {
            val intent = Intent(this, AddNewTaskScreen::class.java)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            startActivity(intent)
        }

        taskViewModel = TaskViewModel(application)

        // Retrieve tasks
        tasks = taskViewModel.getAllTasks()

        // Pass the activity as a listener to the adapter
        updateUI(tasks)

        binding.searchTxt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                filterTasks(s.toString())
            }
        })
    }
    private fun updateUI(tasks: List<Task>) {

        // Initialize RecyclerView
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Pass the activity as a listener to the adapter
        taskAdapter = TaskAdapter(tasks, this)
        recyclerView.adapter = taskAdapter

        if (tasks.isEmpty()) {
            binding.emptyView.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.emptyView.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }

    private fun filterTasks(query: String) {
        val filteredTasks = tasks.filter {
            it.taskTitle.contains(query, ignoreCase = true) || it.taskDescription.contains(query, ignoreCase = true)
        }
        taskAdapter.updateTasks(filteredTasks)
    }



}