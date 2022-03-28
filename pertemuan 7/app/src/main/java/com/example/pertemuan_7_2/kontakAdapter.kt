package com.example.pertemuan_7_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.kontaklist.view.*
import java.util.ArrayList

class kontakAdapter: RecyclerView.Adapter<kontakAdapter.ListViewHolder>() {

    private lateinit var  onItemClickListener: OnItemClickListener

    private val listkontak= ArrayList<kontakk>()

    fun setOnItemCLickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener=onItemClickListener
    }

    fun setData(itemData:ArrayList<kontakk>){
        listkontak.clear()
        listkontak.addAll(itemData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(kontakk: kontakk){
            with(itemView) {
                Glide.with(context).load(kontakk.image).into(kontak_foto)
                judul.text =kontakk.name
                itemView.setOnClickListener {
                    onItemClickListener.onItemClicked(listkontak[adapterPosition])
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kontakAdapter.ListViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.kontaklist,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: kontakAdapter.ListViewHolder, position: Int) {
        holder.bind(listkontak[position])
    }

    override fun getItemCount(): Int = listkontak.size

    interface OnItemClickListener{
        fun onItemClicked(kontakk:kontakk)
    }
}
