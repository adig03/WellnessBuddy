package com.example.wellnessbuddy.auth.Fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.wellnessbuddy.R
import com.example.wellnessbuddy.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

@Suppress("DEPRECATION")
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth
//    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
//        progressDialog = ProgressDialog(activity)

        binding.signupBtn.setOnClickListener {
            val name = binding.UserName.text.toString().trim()
            val email = binding.signupEmail.text.toString().trim()
            val phoneNumber = binding.signupPhoneNumber.text.toString().trim()
            val password = binding.SignupPasswordET.text.toString().trim()

            if (validateInput(name, email, phoneNumber, password)) {
//                registerUser(email, password)
                findNavController().navigate(R.id.action_signUpFragment_to_otpFragment)
            }
        }
        binding.signInText.setOnClickListener{
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

    private fun validateInput(name: String, email: String, phoneNumber: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                binding.UserName.error = "Name is required"
                false
            }
            TextUtils.isEmpty(email) -> {
                binding.signupEmail.error = "Email is required"
                false
            }
            TextUtils.isEmpty(phoneNumber) -> {
                binding.signupPhoneNumber.error = "Phone number is required"
                false
            }
            getPasswordStrength(password) < 1 -> {
                binding.SignupPasswordET.error = "Password is too weak"
                false
            }
            else -> true
        }
    }

    private fun getPasswordStrength(password: String): Int {
        var strength = 0
        if (password.length >= 6) strength++
        if (password.any { it.isDigit() }) strength++
        if (password.any { it.isUpperCase() }) strength++
        if (password.any { it.isLowerCase() }) strength++
        if (password.any { !it.isLetterOrDigit() }) strength++
        return when {
            strength <= 2 -> 0
            strength == 3 || strength == 4 -> 1
            else -> 2
        }
    }

//    private fun registerUser(email: String, password: String) {
//        progressDialog.setMessage("Registering user...")
//        progressDialog.show()
//
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                progressDialog.dismiss()
//                if (task.isSuccessful) {
//                    Toast.makeText(activity, "Registration successful!", Toast.LENGTH_SHORT).show()
//                    // Navigate to the next screen if needed
//                } else {
//                    Toast.makeText(activity, "Registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
//                }
//            }
//    }
}
