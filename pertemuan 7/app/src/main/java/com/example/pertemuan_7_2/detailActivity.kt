package com.example.pertemuan_7_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class detailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_KONTAKK = "extra_kontakk"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent.getParcelableExtra<kontakk>(EXTRA_KONTAKK)

        Glide.with(this).load(intent?.image).into(detail_image)
        detail_name.text=intent?.name

    }
}