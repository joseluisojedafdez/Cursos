package com.i4bchile.centrodeformacinfuturo.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.i4bchile.centrodeformacinfuturo.databinding.ItemListCourseBinding
import com.i4bchile.centrodeformacinfuturo.model.Course
import com.i4bchile.centrodeformacinfuturo.util.convertToWeeks

class CourseAdapter:RecyclerView.Adapter<CourseVH>() {

    private var courseList=listOf<Course>()
    private val selected= MutableLiveData<Course>()
    fun getSelected(): LiveData<Course> {
        return selected
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseVH {
        val binding=ItemListCourseBinding.inflate(LayoutInflater.from(parent.context))

        return CourseVH(binding)
    }

    override fun onBindViewHolder(holder: CourseVH, position: Int) {
        val cake=courseList[position]
        holder.bind(cake)
        holder.itemView.setOnClickListener{
            selected.value=cake
        }
    }

    override fun getItemCount()=courseList.size

    fun updateList(list: List<Course>) {
        courseList=list
        notifyDataSetChanged()
    }


}

class CourseVH (private val binding: ItemListCourseBinding): RecyclerView.ViewHolder(binding.root) {


    fun bind(course: Course) {

        binding.tvNombreListado.text=course.title
        binding.tvWeeksListado.text=course.weeks.convertToWeeks()
        binding.imgListado.load(course.image)

        binding.tvStartList.text=course.start

    }


}
