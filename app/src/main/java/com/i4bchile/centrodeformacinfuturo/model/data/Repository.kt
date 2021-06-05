package com.i4bchile.centrodeformacinfuturo.model.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.i4bchile.centrodeformacinfuturo.model.CourseDetail

class Repository {
    private val database = CourseApplication.courseDatabase!!
    val courseList = database.courseDao().getAllCourses()

    suspend fun getCourseList() {

        val response = RetrofitClient.retrofitInstance().getCourseList()



        when (response.isSuccessful) {

            true -> {
                Log.d("Repository", "getCakeList con: ${response.body()?.size} elementos")
                response.body()?.let {
                    database.courseDao().loadAllCourses(it)
                }
            }

            false -> {
                Log.d("Repository", "error de conexión: ${response.code()} ")
            }
        }
    }

    suspend fun getCourseDetail(id: String) {
        val response = RetrofitClient.retrofitInstance().getCourseDetail(id)

        when (response.isSuccessful) {

            true -> {
                Log.d("Repository", "Inserting Detail ${response.body()}")
                response.body()?.let {
                    database.courseDao().loadCourseDetail(it)
                }

            }
            false -> {
                Log.d("Repository", "Connection Error ${response.code()}")
            }
        }

    }

    fun getDetail(id: String): LiveData<CourseDetail> {

        return database.courseDao().getCourseDetail(id)

    }
}

