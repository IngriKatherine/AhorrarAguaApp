package com.example.ahorraaguaapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.ahorraaguaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.BotonTips.setOnClickListener {
            startActivity(Intent(this, TipsActivity::class.java))
        }
        binding.BotonArticulos.setOnClickListener {
            startActivity(Intent(this, ArticulosActivity::class.java))
        }
        binding.BotonJuego.setOnClickListener {
            startActivity(Intent(this, JuegoActivity::class.java))
        }
        binding.BotonHabitos.setOnClickListener {
            startActivity(Intent(this, Habitos::class.java))
        }
    }
}

@Composable
fun ActivityMainPreview(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    AndroidView(
        factory = {
            val layoutInflater = android.view.LayoutInflater.from(context)
            val binding = ActivityMainBinding.inflate(layoutInflater)
            binding.root
        },
        modifier = modifier
    )
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun PreviewActivityMain() {
    ActivityMainPreview(modifier = Modifier)
}