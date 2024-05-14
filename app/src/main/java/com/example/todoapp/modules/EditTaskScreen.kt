package com.example.todoapp.modules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityEditBinding
import com.example.todoapp.databinding.ActivityHomeBinding

class EditTaskScreen : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}