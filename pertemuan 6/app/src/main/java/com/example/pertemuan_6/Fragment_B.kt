package com.example.pertemuan_6

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class Fragment_B: Fragment() {
    override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
        val k = inflater.inflate(R.layout.fragment_b, container, false)
        val Hal3 = k.findViewById<Button>(R.id.Hal3)
        Hal3.setOnClickListener {
            val i = Intent(context, Lembar3::class.java);
            startActivity(i)
        }
        return k
}
}