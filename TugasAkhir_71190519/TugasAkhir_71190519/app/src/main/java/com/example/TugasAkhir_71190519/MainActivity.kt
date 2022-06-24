package com.example.TugasAkhir_71190519

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider

class MainActivity : AppCompatActivity() {

    private lateinit var loginEmail: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

       loginEmail = FirebaseAuth.getInstance()

        val username = findViewById<EditText>(R.id.et_username)
        val password = findViewById<EditText>(R.id.et_password)
        val btnLogin = findViewById<Button>(R.id.bt_submit)
        val btnLoginEmail = findViewById<Button>(R.id.bt_loginEmail)

        btnLogin.setOnClickListener {
            val pw = password.text.toString()
            if(pw == "1234"){
                val i = Intent(this, SecondActivity::class.java);
                startActivity(i)
            }
            else{
                Toast.makeText(this,"Wrong Password, Hint: 1234", Toast.LENGTH_SHORT).show()
            }
        }

        btnLoginEmail.setOnClickListener{
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                username.text.toString(),
                password.text.toString())
                .addOnCompleteListener{
                    if (it.isSuccessful){
                        Toast.makeText(this,"Berhasil Login Email", Toast.LENGTH_SHORT).show()
                        val i = Intent(this, SecondActivity::class.java);
                        i.putExtra("username", username.text.toString())
                        startActivity(i)
                    }
                    else{
                        Toast.makeText(this,"Gagal Login Email", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}