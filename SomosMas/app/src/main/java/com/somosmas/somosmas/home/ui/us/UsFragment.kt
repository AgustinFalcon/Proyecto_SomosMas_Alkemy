package com.somosmas.somosmas.home.ui.us

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.somosmas.somosmas.databinding.FragmentUsBinding

class UsFragment : Fragment() {

    private lateinit var usViewModel: UsViewModel
    private var _binding: FragmentUsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        usViewModel =
            ViewModelProvider(this).get(UsViewModel::class.java)

        _binding = FragmentUsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.fragUs
        usViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}