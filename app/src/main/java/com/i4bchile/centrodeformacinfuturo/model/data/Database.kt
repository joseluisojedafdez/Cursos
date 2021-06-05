package com.i4bchile.centrodeformacinfuturo.model.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Database
import com.i4bchile.centrodeformacinfuturo.model.Course
import com.i4bchile.centrodeformacinfuturo.model.CourseDetail

class Database {

    @Dao
    interface CourseDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun loadAllCourses(productList: List<Course>)

        @Query("SELECT * FROM course")
        fun getAllCourses(): LiveData<List<Course>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun loadCourseDetail(courseDetail: CourseDetail)

        @Query("SELECT *FROM course_detail WHERE id=:pId")
        fun getCourseDetail(pId: String): LiveData<CourseDetail>

    }

    @Database(entities = [Course::class, CourseDetail::class], version = 1)
    abstract class CourseDatabase : RoomDatabase() {
        abstract fun courseDao(): CourseDao
    }


}

class CourseApplication : Application() {
    companion object {
       var courseDatabase: com.i4bchile.centrodeformacinfuturo.model.data.Database.CourseDatabase? =
            null

    }

    override fun onCreate() {
        super.onCreate()

        courseDatabase = Room.databaseBuilder(
            this,
            com.i4bchile.centrodeformacinfuturo.model.data.Database.CourseDatabase::class.java,
            "course_db"
        ).build()
    }
}
