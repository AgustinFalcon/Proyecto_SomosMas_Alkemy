package com.somosmas.somosmas.home.ui.contributie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.somosmas.somosmas.databinding.FragmentContributieBinding

class ContributieFragment : Fragment() {

    private lateinit var contributieViewModel: ContributieViewModel
    private var _binding: FragmentContributieBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contributieViewModel =
            ViewModelProvider(this).get(ContributieViewModel::class.java)

        _binding = FragmentContributieBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.fragContributie
        contributieViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}