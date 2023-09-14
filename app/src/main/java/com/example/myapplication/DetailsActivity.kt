package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

         var bundle : Bundle? = intent.extras
        var name = bundle?.getString("name")
        var age = bundle?.getString("age")
        var city = bundle?.getString("city")

        binding.apply {
            textViewName.text = name
            textViewAge.text = age
            textViewCity.text = city
        }
    }
}