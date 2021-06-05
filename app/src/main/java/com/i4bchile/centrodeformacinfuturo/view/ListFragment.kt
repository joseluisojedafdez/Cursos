package com.i4bchile.centrodeformacinfuturo.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.i4bchile.centrodeformacinfuturo.R
import com.i4bchile.centrodeformacinfuturo.databinding.FragmentListBinding
import com.i4bchile.centrodeformacinfuturo.viewmodel.CourseVM


class ListFragment : Fragment() {

    private val viewModel: CourseVM by activityViewModels()
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater)
        val adapter = CourseAdapter()
        binding.rvLista.adapter = adapter
        binding.rvLista.layoutManager = GridLayoutManager(this.context, 1)
        viewModel.courseList.observe(viewLifecycleOwner) { courseList ->
            courseList.let {
                adapter.updateList(courseList)
            }
        }

        adapter.getSelected().observe(viewLifecycleOwner, { course ->
            course?.let {

                viewModel.setSelected(it)

            }
            Log.d("List", "onCreateView: $course")
            activity
                ?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.main_container, DetailFragment(course.id))
                ?.addToBackStack("volver")
                ?.commit()
        })
        return binding.root
    }


}