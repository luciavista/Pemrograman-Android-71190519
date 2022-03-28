package com.example.pertemuan_7_2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class kontakk(
    val image: Int,
    val name: String
) : Parcelable