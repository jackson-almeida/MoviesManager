package com.example.moviesmanager.model.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull

@Parcelize
@Entity(
    indices = [
        Index(
            value = ["nome"],
            unique = true
        )
    ]
)
data class Movie (
    @PrimaryKey(autoGenerate = false)
    var id: Int,

    @NotNull
    var nome: String,

    @NonNull
    var anoLancamento: Int,

    @NonNull
    var estudio: String,

    @NonNull
    var duracao: Int,

    @NonNull
    var flag: Boolean,

    var nota: Int,

    @NonNull
    var genero: String
): Parcelable