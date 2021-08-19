package com.somosmas.somosmas.home.ui.testimonios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.somosmas.somosmas.databinding.FragmentTestimoniosBinding

class TestimoniosFragment : Fragment() {

    private lateinit var testimoniosViewModel: TestimoniosViewModel

    private var _binding: FragmentTestimoniosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        testimoniosViewModel =
            ViewModelProvider(this).get(TestimoniosViewModel::class.java)

        _binding = FragmentTestimoniosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.fragTestimonios
        testimoniosViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}