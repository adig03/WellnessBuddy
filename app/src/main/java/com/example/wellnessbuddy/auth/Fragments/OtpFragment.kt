package com.example.wellnessbuddy.auth.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.wellnessbuddy.R
import com.example.wellnessbuddy.databinding.FragmentOtpBinding


class OtpFragment : Fragment(R.layout.fragment_otp) {

private lateinit var binding : FragmentOtpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_otp, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
           binding.otpBtn.setOnClickListener{
               findNavController().navigate(R.id.action_otpFragment_to_confirmFragment)
           }
        binding.otpToolbar.setNavigationOnClickListener{
            findNavController().navigate(R.id.action_otpFragment_to_signUpFragment)
        }
    }


}