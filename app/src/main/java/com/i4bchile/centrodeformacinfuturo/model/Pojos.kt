package com.i4bchile.centrodeformacinfuturo.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "course")
data class Course(
    @PrimaryKey val id: String,
    val title: String,
    val previewDescription: String,
    val image: String,
    val weeks: Int,
    val start: String
)

@Entity(tableName = "course_detail")
data class CourseDetail(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val image: String,
    val weeks: Int,
    val tuition: String,
    val minimumSkill: String,
    val scholarshipsAvailable: Boolean,
    val modality: String,
    val start: String,
)
