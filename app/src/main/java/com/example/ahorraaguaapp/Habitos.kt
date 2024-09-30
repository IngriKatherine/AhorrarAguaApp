package com.example.ahorraaguaapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Habitos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(JuegoView(this))
    }
}