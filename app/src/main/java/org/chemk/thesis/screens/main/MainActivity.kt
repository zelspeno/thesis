package org.chemk.thesis.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.chemk.thesis.R
import org.chemk.thesis.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide() // закрыть верхнюю плашку с названием приложения
    }
}