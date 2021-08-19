package com.somosmas.somosmas.home.ui.testimony

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.somosmas.somosmas.databinding.FragmentTestimonyBinding

class TestimonyFragment : Fragment() {

    private lateinit var testimonyViewModel: TestimonyViewModel

    private var _binding: FragmentTestimonyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        testimonyViewModel =
            ViewModelProvider(this).get(TestimonyViewModel::class.java)

        _binding = FragmentTestimonyBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.fragTestimony
        testimonyViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}