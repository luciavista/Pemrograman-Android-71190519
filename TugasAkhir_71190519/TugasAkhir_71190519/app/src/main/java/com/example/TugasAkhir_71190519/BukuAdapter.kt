package com.example.TugasAkhir_71190519

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore


class BukuAdapter (val listBuku: ArrayList<Buku>): RecyclerView.Adapter<BukuAdapter.PendudukHolder>(){
    class PendudukHolder(val v: View): RecyclerView.ViewHolder(v){
        var buku: Buku? = null
        val firestore = FirebaseFirestore.getInstance()

        fun bindView(buku: Buku){
            this.buku = buku
            v.findViewById<TextView>(R.id.tv_judulBUKU).text = buku.bukuJudul
            v.findViewById<TextView>(R.id.tv_nama).text = buku.penulisNama
            v.findViewById<TextView>(R.id.tv_terbitTH).text = buku.terbitTH
            v.findViewById<TextView>(R.id.tv_penerbit).text = buku.penerbitNama


            //button delete
            v.findViewById<Button>(R.id.bt_delete).setOnClickListener{

                firestore.collection("Buku")
                    .whereEqualTo("bukuJudul", buku.bukuJudul)
                    .get()
                    .addOnSuccessListener {
                        for (document in it){
                            firestore.collection("Buku").document(document.id).delete()
                                .addOnSuccessListener {
                                    Toast.makeText(v.context,"Data Berhasil dihapus",Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                    .addOnFailureListener{
                        Toast.makeText(v.context,"Data Gagal dihapus",Toast.LENGTH_SHORT).show()
                    }
                (v.context as Activity).recreate()
            }

            //button edit
            v.findViewById<Button>(R.id.bt_edit).setOnClickListener{

                val i = Intent(v.context, EditDataBuku::class.java)
                i.putExtra("judulbuku",buku.bukuJudul)
                i.putExtra("nama",buku.penulisNama)
                i.putExtra("terbitTH",buku.terbitTH)
                i.putExtra("alamat",buku.penerbitNama)
                v.context.startActivity(i)
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuAdapter.PendudukHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_buku, parent, false)
        return PendudukHolder(v)
    }
    //memilih file layout XML yang akan dijadikan container

    override fun onBindViewHolder(holder: BukuAdapter.PendudukHolder, position: Int) {
        //memasang data ke dalam file layout XML yang telah dipilih
        holder.bindView(listBuku[position])
    }

    override fun getItemCount(): Int {
        //mengembalikan jumlah item yang terdapat pada RecyclerView
        return listBuku.size
    }

}
