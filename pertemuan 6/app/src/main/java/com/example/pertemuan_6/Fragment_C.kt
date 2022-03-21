package com.example.pertemuan_6

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class Fragment_C: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val j = inflater.inflate(R.layout.fragment_c, container, false)
        val Hal3 = j.findViewById<Button>(R.id.Hal1)
        Hal3.setOnClickListener {
            val i = Intent(context, MainActivity::class.java);
            startActivity(i)
        }
        return j

    }
}