package com.example.pertemuan11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    var firestore: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firestore = FirebaseFirestore.getInstance()

        val edtNama = findViewById<EditText>(R.id.nama)
        val edtNIM = findViewById<EditText>(R.id.NIM)
        val edtIPK = findViewById<EditText>(R.id.IPK)
        val txtColumnNIM = findViewById<TextView>(R.id.txtColumnNim)
        val txtColumnNama = findViewById<TextView>(R.id.txtColumnNama)
        val txtColumnIpk = findViewById<TextView>(R.id.txtColumnIpK)

        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnCariData = findViewById<Button>(R.id.Dataa)
        val btnIPKAsc = findViewById<Button>(R.id.btnIPK)
        val btnNamaAsc = findViewById<Button>(R.id.btnNama)


        var listMahasiswa2 = arrayListOf<Mahasiswa>()



        btnSimpan.setOnClickListener {
            if (edtNama.text.toString() != "" && edtNIM.text.toString() != "" && edtIPK.text.toString() != "") {
                val mahasiswa = Mahasiswa(
                    edtNama.text.toString(),
                    edtNIM.text.toString(),
                    edtIPK.text.toString()
                )
                firestore?.collection("Mahasiswa")?.add(mahasiswa)

                Toast.makeText(
                    this,
                    "Data ${edtNama.text.toString()} berhasil ditambahkan",
                    Toast.LENGTH_LONG
                ).show()
                edtNama.setText("")
                edtNIM.setText("")
                edtIPK.setText("")
            } else {
                Toast.makeText(
                    this,
                    "Simpan data gagal. Pastikan semua kolom sudah terisi",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        btnCariData.setOnClickListener {
            if (edtNama.text.toString() != "" && edtNama.text.toString() != null) {
                txtColumnNIM.setText("")
                txtColumnNama.setText("")
                txtColumnIpk.setText("")
                firestore?.collection("Mahasiswa")?.whereEqualTo("nama", edtNama.text.toString())
                    ?.get()?.addOnSuccessListener { hasil ->
                        for (d in hasil) {
                            txtColumnNIM.setText("${d.data["nim"]}\n\n")
                            txtColumnNama.setText("${d.data["nama"]}\n\n")
                            txtColumnIpk.setText("${d.data["ipk"]}\n\n")

                        }
                    }
            } else if (edtNIM.text.toString() != "" && edtNIM.text.toString() != null) {
                txtColumnNIM.setText("")
                txtColumnNama.setText("")
                txtColumnIpk.setText("")

                firestore?.collection("Mahasiswa")?.whereEqualTo("nim", edtNIM.text.toString())
                    ?.get()?.addOnSuccessListener { hasil ->
                        for (d in hasil) {
                            txtColumnNIM.setText("${d.data["nim"]}\n\n")
                            txtColumnNama.setText("${d.data["nama"]}\n\n")
                            txtColumnIpk.setText("${d.data["ipk"]}\n\n")
                        }
                    }
            } else if (edtIPK.text.toString() != "" && edtIPK.text.toString() != null) {
                txtColumnNIM.setText("")
                txtColumnNama.setText("")
                txtColumnIpk.setText("")

                firestore?.collection("Mahasiswa")?.whereEqualTo("ipk", edtIPK.text.toString())
                    ?.get()?.addOnSuccessListener { hasil ->
                        for (d in hasil) {
                            txtColumnNIM.setText("${txtColumnNIM.text.toString()} ${d.data["nim"]}\n\n")
                            txtColumnNama.setText("${txtColumnNama.text.toString()} ${d.data["nama"]}\n\n")
                            txtColumnIpk.setText("${txtColumnIpk.text.toString()} ${d.data["ipk"]}\n\n")
                            listMahasiswa2.add(
                                Mahasiswa(
                                    d.data["nama"] as String,
                                    d.data["nim"] as String,
                                    d.data["ipk"] as String
                                )
                            )


                        }
                        Toast.makeText(this, "Memproses Pencarian IPK", Toast.LENGTH_LONG).show()
                    }?.addOnFailureListener {

                        txtColumnNIM.setText("")
                        txtColumnNama.setText("")
                        txtColumnIpk.setText("")

                        firestore?.collection("Mahasiswa")?.get()?.addOnSuccessListener { hasil ->
                            for (d in hasil) {
                                txtColumnNIM.setText("${txtColumnNIM.text.toString()} ${d.data["nim"]}\n\n")
                                txtColumnNama.setText("${txtColumnNama.text.toString()} ${d.data["nama"]}\n\n")
                                txtColumnIpk.setText("${txtColumnIpk.text.toString()} ${d.data["ipk"]}\n\n")
                                listMahasiswa2.add(
                                    Mahasiswa(
                                        d.data["nama"] as String,
                                        d.data["nim"] as String,
                                        d.data["ipk"] as String
                                    )
                                )
                            }
                            Toast.makeText(this, "Memproses semua data", Toast.LENGTH_LONG).show()
                        }

                    }
            }

            btnNamaAsc.setOnClickListener {
                if (listMahasiswa2.isEmpty()) {
                    firestore?.collection("Mahasiswa")?.get()?.addOnSuccessListener { hasil ->
                        for (d in hasil) {
                            listMahasiswa2.add(
                                Mahasiswa(
                                    d.data["nama"] as String,
                                    d.data["nim"] as String,
                                    d.data["ipk"] as String
                                )
                            )
                        }
//                    Toast.makeText(this,"Memproses semua data",Toast.LENGTH_LONG).show()
                    }
                }

                val sortedList = listMahasiswa2.sortedWith(compareBy({ it.nama }))
                txtColumnNIM.setText("")
                txtColumnNama.setText("")
                txtColumnIpk.setText("")

                for (d: Mahasiswa in sortedList) {
                    txtColumnNIM.setText("${txtColumnNIM.text.toString()} ${d.NIM}\n\n")
                    txtColumnNama.setText("${txtColumnNama.text.toString()} ${d.nama}\n\n")
                    txtColumnIpk.setText("${txtColumnIpk.text.toString()} ${d.IPK}\n\n")
                }
            }

            btnIPKAsc.setOnClickListener {
                if (listMahasiswa2.isEmpty()) {
                    firestore?.collection("Mahasiswa")?.get()?.addOnSuccessListener { hasil ->
                        for (d in hasil) {
                            listMahasiswa2.add(
                                Mahasiswa(
                                    d.data["nama"] as String,
                                    d.data["nim"] as String,
                                    d.data["ipk"] as String
                                )
                            )
                        }

                    }
                }

                val sortedList = listMahasiswa2.sortedWith(compareBy({ it.IPK }))
                txtColumnNIM.setText("")
                txtColumnNama.setText("")
                txtColumnIpk.setText("")

                for (d: Mahasiswa in sortedList) {
                    txtColumnNIM.setText("${txtColumnNIM.text.toString()} ${d.NIM}\n\n")
                    txtColumnNama.setText("${txtColumnNama.text.toString()} ${d.nama}\n\n")
                    txtColumnIpk.setText("${txtColumnIpk.text.toString()} ${d.IPK}\n\n")
                }
            }
        }
    }
}