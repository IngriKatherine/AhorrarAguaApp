package com.example.ahorraaguaapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.example.ahorraaguaapp.databinding.ActivityMainBinding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listeners using view binding
        binding.tipsButton.setOnClickListener {
            startActivity(Intent(this, TipsActivity::class.java))
        }

        binding.articlesButton.setOnClickListener {
            startActivity(Intent(this, ArticulosActivity::class.java))
        }

        binding.gameButton.setOnClickListener {
            startActivity(Intent(this, JuegoActivity::class.java))
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
        Greeting("Android")
}