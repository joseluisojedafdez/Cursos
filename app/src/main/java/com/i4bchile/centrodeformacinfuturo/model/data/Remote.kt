package com.i4bchile.centrodeformacinfuturo.model.data

import com.i4bchile.centrodeformacinfuturo.model.Course
import com.i4bchile.centrodeformacinfuturo.model.CourseDetail
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CourseAPI {
    @GET("courses")
    suspend fun getCourseList(): Response<List<Course>>

    @GET("courses_detail/{id}")
    suspend fun getCourseDetail(@Path("id") id: String): Response<CourseDetail>
}

class RetrofitClient {
    companion object {
        private const val BASE_URL = "https://courses-fake-api.herokuapp.com/"

        fun retrofitInstance(): CourseAPI {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
                GsonConverterFactory.create()
            )
                .build()

            return retrofit.create(CourseAPI::class.java)
        }
    }
}