package com.example.bookcave.db

import androidx.room.Dao
import androidx.room.Query
import com.example.bookcave.db.models.Course
import com.example.bookcave.db.models.Lecture

@Dao
interface Dao {

    @Query("SELECT * FROM Lecture")
    fun getAllLectures() : List<Lecture>

    @Query("SELECT * FROM Course")
    fun getAllCourses() : List<Course>

    @Query("SELECT * FROM Course WHERE id = :courseId")
    fun getCourseById(courseId: Int): Course

    @Query("SELECT * FROM Lecture WHERE courseId = :courseId")
    fun getLectures(courseId: Int): List<Lecture>

}