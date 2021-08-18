package com.somosmas.somosmas.home.ui.contacto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.somosmas.somosmas.databinding.FragmentContactoBinding

class ContactoFragment : Fragment() {

    private lateinit var contactoViewModel: ContactoViewModel
    private var _binding: FragmentContactoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contactoViewModel =
            ViewModelProvider(this).get(ContactoViewModel::class.java)

        _binding = FragmentContactoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.txtContacto
        contactoViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}