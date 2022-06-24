package com.example.TugasAkhir_71190519

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class LihatDataBuku: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihatdatabuku)

        val firestore = FirebaseFirestore.getInstance()

        val btnMenu = findViewById<Button>(R.id.bt_menu)

        btnMenu.setOnClickListener {
            val i = Intent(this, SecondActivity::class.java)
            startActivity(i)
        }

        firestore.collection("Buku")
            .get()
            .addOnSuccessListener { hasilPencarian->

                val listPenduduk = ArrayList<Buku>()

                for (document in hasilPencarian){
                    listPenduduk.add(Buku("${document.data["bukuJudul"]}","${document.data["penulisNama"]}", "${document.data["terbitTH"]}", "${document.data["penerbitNama"]}"))
                }

                //siapkan RecyclerView
                val rvPenduduk = findViewById<RecyclerView>(R.id.rvListBuku)
                rvPenduduk.layoutManager = LinearLayoutManager (this)
                val adapter = BukuAdapter(listPenduduk)
                rvPenduduk.adapter = adapter
            }
            .addOnFailureListener{
                Log.d("Gagal", "Pengambilan Dokumen Gagal")
            }

    }
}