package com.somosmas.somosmas.home.ui.novedades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.somosmas.somosmas.databinding.FragmentActividadesBinding
import com.somosmas.somosmas.databinding.FragmentNovedadesBinding


class NovedadesFragment : Fragment() {

    private lateinit var novedadesViewModel: NovedadesViewModel
    private var _binding: FragmentNovedadesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        novedadesViewModel =
            ViewModelProvider(this).get(NovedadesViewModel::class.java)

        _binding = FragmentNovedadesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.txtNovedades
        novedadesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}