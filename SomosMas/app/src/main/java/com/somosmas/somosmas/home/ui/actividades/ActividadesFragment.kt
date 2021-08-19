package com.somosmas.somosmas.home.ui.actividades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.somosmas.somosmas.databinding.FragmentActividadesBinding


class ActividadesFragment : Fragment() {

    private lateinit var actividadesViewModel: ActividadesViewModel
    private var _binding: FragmentActividadesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        actividadesViewModel =
            ViewModelProvider(this).get(ActividadesViewModel::class.java)

        _binding = FragmentActividadesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.fragActividades
        actividadesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}