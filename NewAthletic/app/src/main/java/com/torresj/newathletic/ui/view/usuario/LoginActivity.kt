package com.torresj.newathletic.ui.view.usuario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.torresj.newathletic.data.model.SeguridadUsuarioLogin
import com.torresj.newathletic.ui.view.MainActivity
import com.torresj.newathletic.databinding.ActivityLoginBinding
import com.torresj.newathletic.ui.viewmodel.usuario.UsuarioViewModel
import com.torresj.newathletic.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val usuarioViewModel: UsuarioViewModel by viewModels()
    private lateinit var usuario: SeguridadUsuarioLogin

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        usuario = SeguridadUsuarioLogin()

        binding.singIn.setOnClickListener {

            usuario.usuario = "CSANCHEZ"
            usuario.clave = "123"

            usuarioViewModel.ingresar(usuario)

           usuarioViewModel.usuario.observe(this, Observer {
                //binding.tvQuote.text = it.quote
                //binding.tvAuthor.text = it.author
               if (it != null) {
                   tokenManager.saveToken(it.token!!)
               }
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            })




        }

    }

    private fun bindObservers() {
        usuarioViewModel.usuario.observe(this, Observer {
          //  binding.progressBar.isVisible = false
            when (it) {


                /* is NetworkResult.Success -> {
                   tokenManager.saveToken(it.data!!.token)
                   findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
               }
               is NetworkResult.Error -> {
                   showValidationErrors(it.message.toString())
               }
             is NetworkResult.Loading ->{
                   binding.progressBar.isVisible = true
               }*/
            }
        })
    }


}