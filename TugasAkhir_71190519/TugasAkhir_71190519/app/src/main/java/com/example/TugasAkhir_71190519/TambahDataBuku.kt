package com.example.TugasAkhir_71190519

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class TambahDataBuku: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambahdatabuku)

        val judulbuku = findViewById<EditText>(R.id.et_judulBuku)
        val nama = findViewById<EditText>(R.id.et_nama)
        val terbitth = findViewById<EditText>(R.id.et_terbitTH)
        val penerbit = findViewById<EditText>(R.id.et_penerbit)

        val btnsubmit = findViewById<Button>(R.id.bt_submit)
        val btnback = findViewById<Button>(R.id.bt_back)

        val firestore = FirebaseFirestore.getInstance()

        btnback.setOnClickListener {
            val i = Intent(this, SecondActivity::class.java);
            startActivity(i)
        }

        btnsubmit.setOnClickListener {
            if (judulbuku.text.toString() != "" && nama.text.toString() != "" && terbitth.text.toString() != "" && penerbit.text.toString() != "") {

                val penduduk = Buku(
                    judulbuku.text.toString(),
                    nama.text.toString(),
                    terbitth.text.toString(),
                    penerbit.text.toString()
                )
                firestore.collection("Buku").add(penduduk)
                Toast.makeText(this, "Data berhasil ditambahkan!", Toast.LENGTH_SHORT).show()

                judulbuku.setText("")
                nama.setText("")
                terbitth.setText("")
                penerbit.setText("")
            } else {
                Toast.makeText(this, "Data gagal ditambahkan", Toast.LENGTH_LONG).show()
            }
        }
    }
}