package com.example.pertemuan5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            val user = inputUsername.text.toString()
            val pass = inputPassword.text.toString()
            if (pass == "1234") {
                val i = Intent(this, Home::class.java);
                i.putExtra("username", user.toString())
                startActivity(i)
            } else {
                Toast.makeText(applicationContext, "Username atau Password SALAH!!!", Toast.LENGTH_LONG).show()
            }
        }

    }
}