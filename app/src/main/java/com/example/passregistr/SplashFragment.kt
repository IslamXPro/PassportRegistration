package com.example.passregistr

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.passregistr.Utils.Back

class SplashFragment : Fragment() {

    lateinit var handler: Handler
    lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_splash, container, false)

        handler = Handler()
        handler.postDelayed({
            findNavController().navigate(R.id.homeFragment)
        }, 3000)

        Back.isHome = false

        return root
    }
}