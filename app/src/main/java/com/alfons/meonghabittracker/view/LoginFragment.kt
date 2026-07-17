package com.alfons.meonghabittracker.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.alfons.meonghabittracker.databinding.FragmentLoginBinding
import com.alfons.meonghabittracker.model.HabitDatabase
import com.alfons.meonghabittracker.util.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Username & Password tidak boleh kosong!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.Main).launch {
                val db = HabitDatabase.getInstance(requireContext())
                val user = db.userDao().login(username, password)

                withContext(Dispatchers.Main) {
                    if (user != null) {
                        SessionManager.saveUserId(requireContext(), user.userId)

                        val action = LoginFragmentDirections.actionDashboardFragment()
                        findNavController().navigate(action)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Username/Password salah!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}