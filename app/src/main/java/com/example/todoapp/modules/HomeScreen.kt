package com.example.todoapp.modules

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityHomeBinding

class HomeScreen : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

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
    }


}