package com.example.todoapp.modules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityAddNoteBinding
import com.example.todoapp.databinding.ActivityOngoingscreenBinding

class AddNewTaskScreen : AppCompatActivity() {

    private lateinit var binding : ActivityAddNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}