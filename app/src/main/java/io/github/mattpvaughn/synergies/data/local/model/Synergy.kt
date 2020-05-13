package io.github.mattpvaughn.synergies.data.local.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Synergy(
    val name: String = "",
    val firstList: String = "",
    val secondList: String = "",
    val numberOf: Int = 0,
    val description: String = "",
    val lastColumn: String = ""
) : Parcelable

