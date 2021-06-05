package com.i4bchile.centrodeformacinfuturo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.i4bchile.centrodeformacinfuturo.model.Course
import com.i4bchile.centrodeformacinfuturo.model.CourseDetail
import com.i4bchile.centrodeformacinfuturo.model.data.Repository
import kotlinx.coroutines.launch

class CourseVM: ViewModel() {

    private val repository = Repository()
    private val selected = MutableLiveData<Course>()

    fun getSelectedDetail(id: String): LiveData<CourseDetail> {
        return repository.getDetail(id)
    }


    fun setSelected(course:Course){
        selected.value = course
    }


    fun loadSelectedDetail(id: String) {
        Log.d("ViewModel", "inserting course with id: $id")
        viewModelScope.launch {
            selected.value?.let {
                repository.getCourseDetail(id)
            }
        }
    }

    val courseList = repository.courseList

    init {
        viewModelScope.launch {
            repository.getCourseList()
        }
    }



}