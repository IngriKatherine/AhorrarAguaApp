// JuegoActivity.kt
package com.example.ahorraaguaapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class JuegoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(JuegoView(this))
    }
}

