package com.example.ahorraaguaapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.ahorraaguaapp.databinding.ActivityMainBinding

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
fun ActivityMainPreview(modifier: Modifier = Modifier) {
    // Access the current context
    val context = LocalContext.current

    // Use AndroidView to preview activity_main.xml layout in Compose
    AndroidView(
        factory = {
            // Obtain the layout inflater from the context
            val layoutInflater = android.view.LayoutInflater.from(context)
            // Inflate your layout using the view binding
            val binding = ActivityMainBinding.inflate(layoutInflater)
            binding.root
        },
        modifier = modifier
    )
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800) // Adjust the preview size here
@Composable
fun PreviewActivityMain() {
    ActivityMainPreview(modifier = Modifier)
}