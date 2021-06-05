package com.i4bchile.centrodeformacinfuturo.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.i4bchile.centrodeformacinfuturo.databinding.FragmentDetailBinding
import com.i4bchile.centrodeformacinfuturo.model.CourseDetail
import com.i4bchile.centrodeformacinfuturo.util.convertToWeeks
import com.i4bchile.centrodeformacinfuturo.viewmodel.CourseVM

class DetailFragment(val id:String): Fragment() {


    private lateinit var binding: FragmentDetailBinding
    private val viewModel: CourseVM by activityViewModels()
    private var course = CourseDetail("0", "", "", "", 0, "", "", false, "", "")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailBinding.inflate(inflater)
        binding.checkScolarship.isClickable = false
        viewModel.loadSelectedDetail(id)
        viewModel.getSelectedDetail(id).observe(viewLifecycleOwner, {
            Log.d("Detail", "onCreateView: $it")
            it?.let {
                course = it
                binding.checkScolarship.isChecked = it.scholarshipAvailable
                binding.imgDetailImage.load(it.image)
                binding.tvDetailTitle.text = it.title
                binding.tvTuition.text = it.tuition
                binding.tvDescriptionDetail.text = it.description
                binding.tvWeeks.text = it.weeks.convertToWeeks()
                binding.tvModality.text = it.modality
            }

        })

        binding.abSendEmail.setOnClickListener {

            val address = arrayOf("admisión@centrofuturo.cl")
            val subject = "Solicito información sobre este curso: id ${course.id}"
            val text = "“Hola\n" +
                    "Quisiera pedir información sobre este curso ${course.title} y me gustaría que me contactaran a este correo o al\n" +
                    "siguiente número _________(indique número aquí)\n" +
                    "Quedo atento.”\n"

            composeEmail(address, subject, text)
        }


        return binding.root
    }

    private fun composeEmail(address: Array<String>, subject: String, text: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, address)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, text)
        }

        startActivity(intent)

    }




}