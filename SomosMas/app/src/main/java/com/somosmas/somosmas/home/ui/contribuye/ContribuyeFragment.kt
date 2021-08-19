package com.somosmas.somosmas.home.ui.contribuye

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.somosmas.somosmas.databinding.FragmentActividadesBinding
import com.somosmas.somosmas.databinding.FragmentContribuyeBinding
import com.somosmas.somosmas.home.ui.actividades.ActividadesViewModel

class ContribuyeFragment : Fragment() {

    private lateinit var contribuyeViewModel: ContribuyeViewModel
    private var _binding: FragmentContribuyeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contribuyeViewModel =
            ViewModelProvider(this).get(ContribuyeViewModel::class.java)

        _binding = FragmentContribuyeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.fragContribuye
        contribuyeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}