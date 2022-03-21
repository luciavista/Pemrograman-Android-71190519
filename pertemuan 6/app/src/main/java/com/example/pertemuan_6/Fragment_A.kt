package com.example.pertemuan_6

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class Fragment_A: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val v = inflater.inflate(R.layout.fragment_a, container, false)
        val Hal2 = v.findViewById<Button>(R.id.Hal2)
        Hal2.setOnClickListener {
            val i = Intent(context, Lembar2::class.java);
            startActivity(i)
        }
        return v


    }
}