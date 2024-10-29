package com.example.wellnessbuddy.auth.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.wellnessbuddy.MainActivity
import com.example.wellnessbuddy.R
import com.example.wellnessbuddy.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = DataBindingUtil.inflate(inflater , R.layout.fragment_login , container , false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.LoginBtn.setOnClickListener {
            val i = Intent(activity  ,MainActivity::class.java)
            startActivity(i)
        }
        binding.signUpText.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        binding.forgotText.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_forgotFragment)
        }
    }


}