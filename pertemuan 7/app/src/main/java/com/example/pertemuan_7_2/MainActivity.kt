package com.example.pertemuan_7_2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pertemuan_7_2.detailActivity.Companion.EXTRA_KONTAKK
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val kontakAdapter = kontakAdapter()
    private val listKontakk = ArrayList<kontakk>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fromResourceToList()

        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager= LinearLayoutManager(this)
        recycler_view.adapter=kontakAdapter

            sendDataToDetail()


    }


    @SuppressLint("Recycle")
    private fun fromResourceToList() {
        val resourceImage = resources.obtainTypedArray(R.array.image)
        val resourceName = resources.getStringArray(R.array.nama)

        for (i in resourceName.indices){
            val itemkontakk = kontakk (
                resourceImage.getResourceId(i, -1),
                resourceName[i]
            )

            listKontakk.add(itemkontakk)
        }
        kontakAdapter.setData(listKontakk)
    }
    private fun sendDataToDetail(){
        kontakAdapter.setOnItemCLickListener(object : kontakAdapter.OnItemClickListener{
            override fun onItemClicked(kontakk: kontakk) {
                val kontakkParcel = kontakk(
                    kontakk.image,
                    kontakk.name
                )
                val intent = Intent(this@MainActivity,detailActivity::class.java)
                intent.putExtra(EXTRA_KONTAKK,kontakkParcel)
                startActivity(intent)
            }
        })
    }
}



