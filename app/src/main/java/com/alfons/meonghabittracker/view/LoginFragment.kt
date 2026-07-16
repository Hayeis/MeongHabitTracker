package com.alfons.meonghabittracker.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.alfons.meonghabittracker.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(
            inflater,
            container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()

            if(username == "student" && password == "123" || username == "user" && password == "123"){
                val action = LoginFragmentDirections.actionDashboardFragment()
                it.findNavController().navigate(action)
            }
            else{
                binding.txtPassword.error= "Username atau Password salah!"
                Toast.makeText(requireContext(), "Login Gagal!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}