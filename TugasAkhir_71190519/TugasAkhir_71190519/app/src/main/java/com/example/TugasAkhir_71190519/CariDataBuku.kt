package com.example.TugasAkhir_71190519

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class CariDataBuku : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cari_data_buku)



        val nama = findViewById<EditText>(R.id.et_nama)

        val judulBuku = findViewById<TextView>(R.id.tv_judulBUKU)
        val namaPenulis = findViewById<TextView>(R.id.tv_nama)
        val tahunTerbit = findViewById<TextView>(R.id.tv_terbitTH)
        val namaPenerbit = findViewById<TextView>(R.id.tv_penerbit)


        val btnCari = findViewById<Button>(R.id.bt_cari)
        val btnBack = findViewById<Button>(R.id.bt_back)

        val firestore = FirebaseFirestore.getInstance()

        btnCari.setOnClickListener {
            firestore.collection("Buku")
                .whereEqualTo("penulisNama", nama.text.toString())
                .get()
                .addOnSuccessListener {

                    for (document in it){
                        judulBuku.setText("${document.data["bukuJudul"]}")
                        namaPenulis.setText("${document.data["penulisNama"]}")
                        tahunTerbit.setText("${document.data["terbutTahun"]}")
                        namaPenerbit.setText("${document.data["penerbitNama"]}")
                    }
                    Toast.makeText(this,"Pencarian ditemukan", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Log.d("Gagal", "Data yang dicari tidak ditemukan")
                }
        }

        btnBack.setOnClickListener {
            val i = Intent(this, SecondActivity::class.java)
            startActivity(i)
        }

    }
}