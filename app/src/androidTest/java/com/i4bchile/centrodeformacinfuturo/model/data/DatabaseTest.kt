package com.i4bchile.centrodeformacinfuturo.model.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.i4bchile.centrodeformacinfuturo.model.Course
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import com.google.common.truth.Truth.assertThat

import org.junit.Rule
import org.junit.Test

class DatabaseTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Instancias de DAO y database
    private lateinit var cDao: Database.CourseDao
    private lateinit var db: Database.CourseDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, Database.CourseDatabase::class.java).build()
        cDao = db.courseDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertCourse_empty() = runBlocking {
        // Given
        val courseList = listOf<Course>()

        // When
        cDao.loadAllCourses(courseList)

        // Then
        cDao.getAllCourses().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

}