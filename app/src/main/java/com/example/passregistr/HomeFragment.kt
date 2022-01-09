package com.example.passregistr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.passregistr.Utils.Back
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_home, container, false)

        root.btn_list.setOnClickListener {
            findNavController().navigate(R.id.listFragment)
        }
        root.btn_regis.setOnClickListener {
            findNavController().navigate(R.id.registrFragment)
        }


        return root
    }
}