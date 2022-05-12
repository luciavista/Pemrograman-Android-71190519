package com.example.pertemuan_9

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var  sharedPreferences: SharedPreferences
    var isRemembered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        isRemembered = sharedPreferences.getBoolean("CHECKBOX", false)

        if (isRemembered){

            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }

        loginps.setOnClickListener {
            val name : String = nameps.text.toString()

            val checked :Boolean = checkboxx.isChecked

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("NAME",name)

            editor.putBoolean("CHECKBOX", checked)
            editor.apply()

            Toast.makeText(this, "berhasil disimpan", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }
    }
}