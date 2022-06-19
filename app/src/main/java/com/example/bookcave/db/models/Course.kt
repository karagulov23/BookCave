package com.example.bookcave.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val category: String,
    val university: String,
    val description: String,
    val imgName: String
)
