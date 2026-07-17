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
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class LoginFragment : Fragment(), CoroutineScope {
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

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

        if (SessionManager.getUserId(requireContext()) != -1) {
            val action = LoginFragmentDirections.actionDashboardFragment()
            findNavController().navigate(action)
            return
        }

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

            launch {
                val user = withContext(Dispatchers.IO) {
                    val db = HabitDatabase(requireContext())
                    db.userDao().login(username, password)
                }

                if (user != null) {
                    SessionManager.saveUserId(requireContext(), user.userId)
                    val action = LoginFragmentDirections.actionDashboardFragment()
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(requireContext(), "Username/Password salah!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}