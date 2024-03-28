package com.cs4520.assignment5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cs4520.assignment5.databinding.LoginLayoutBinding

class LoginFragment: Fragment(R.layout.login_layout) {
    private var _binding: LoginLayoutBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginLayoutBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.loginButton.setOnClickListener(View.OnClickListener {

            val username = binding.usernameText.text.toString()
            val password = binding.passwordText.text.toString()
            if(username == ("admin") && password == ("admin")){
                findNavController().navigate(R.id.action_loginFragment_to_productListFragment)
            } else {
                val toast = Toast.makeText(context, "Please enter correct username and password", Toast.LENGTH_SHORT)
                toast.show()
            }

            binding.usernameText.setText("")
            binding.passwordText.setText("")
        })
        return view
    }
}