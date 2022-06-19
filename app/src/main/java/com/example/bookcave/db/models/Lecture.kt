package com.example.bookcave.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class Lecture(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description: String,
    val courseId: Int,
    val fileName: String,
    val instructor: String
)
