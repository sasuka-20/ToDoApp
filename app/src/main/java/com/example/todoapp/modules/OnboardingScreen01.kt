package com.example.todoapp.modules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.taskpivot.R
import com.example.taskpivot.databinding.ActivityOngoingscreenBinding

class OnboardingScreen01 : AppCompatActivity() {
    private lateinit var binding : ActivityOngoingscreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityOngoingscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}