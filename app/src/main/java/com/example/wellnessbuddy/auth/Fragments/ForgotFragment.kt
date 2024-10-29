package com.example.wellnessbuddy.auth.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.wellnessbuddy.R
import com.example.wellnessbuddy.databinding.FragmentForgotBinding

class ForgotFragment : Fragment(R.layout.fragment_forgot) {
private lateinit var binding:FragmentForgotBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
  binding = DataBindingUtil.inflate(inflater , R.layout.fragment_forgot , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.forgotPassToolbar.setNavigationOnClickListener{
            findNavController().navigate(R.id.action_forgotFragment_to_loginFragment)
        }
    }


}