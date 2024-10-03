package com.example.ahorraaguaapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ahorraaguaapp.databinding.ActivityTipsBinding

class TipsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTipsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using view binding
        binding = ActivityTipsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}