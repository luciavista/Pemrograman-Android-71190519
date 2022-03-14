package com.example.pertemuan5

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val user = intent.getStringExtra("username")

        val home = findViewById<TextView>(R.id.home)
        home.text = "Selamat Datang $user !!!"
    }
}