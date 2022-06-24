package com.example.TugasAkhir_71190519

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore

class EditDataBuku : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buku_edit)

        val jdBuku = getIntent().getStringExtra("judulBuku")
        val nama = getIntent().getStringExtra("nama")
        val noTH = getIntent().getStringExtra("noTH")
        val penerbit = getIntent().getStringExtra("penerbit")

        val noKTPPenduduk = findViewById<TextView>(R.id.tv_judulBUKU)
        noKTPPenduduk.setText("${jdBuku}")

        val namaPenduduk = findViewById<EditText>(R.id.et_nama)
        namaPenduduk.setText("${nama}")

        val noHPPenduduk = findViewById<EditText>(R.id.et_terbitTH)
        noHPPenduduk.setText("${noTH}")

        val alamatPenduduk = findViewById<EditText>(R.id.et_penerbit)
        alamatPenduduk.setText("${penerbit}")

        val btnEdit = findViewById<Button>(R.id.bt_edit)
        val btnBack = findViewById<Button>(R.id.bt_back)
        val firestore = FirebaseFirestore.getInstance()

        btnEdit.setOnClickListener {
            firestore.collection("Buku")
                    .whereEqualTo("bukuJudul", noKTPPenduduk.text.toString())
                    .get()
                    .addOnSuccessListener {
                        for (document in it){

                            firestore.collection("Penduduk").document(document.id)
                                .update("penulisNama",namaPenduduk.text.toString(),"terbitTH",noHPPenduduk.text.toString(),"penerbitNama",alamatPenduduk.text.toString())
                                .addOnSuccessListener {
                                    Toast.makeText(this,"Data berhasil diubah",Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                    .addOnFailureListener{
                        Log.d("Gagal", "Data gagal diubah")
                    }
        }

        btnBack.setOnClickListener { 
            val i = Intent(this, LihatDataBuku::class.java)
            startActivity(i)
        }
    }
}