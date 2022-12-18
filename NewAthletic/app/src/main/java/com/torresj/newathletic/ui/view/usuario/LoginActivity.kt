package com.torresj.newathletic.ui.view.usuario

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.torresj.newathletic.data.model.SeguridadUsuarioLogin
import com.torresj.newathletic.databinding.ActivityLoginBinding
import com.torresj.newathletic.ui.view.MainActivity
import com.torresj.newathletic.ui.view.SplashActivity
import com.torresj.newathletic.ui.viewmodel.usuario.UsuarioViewModel
import com.torresj.newathletic.utils.MensajesGenericos
import com.torresj.newathletic.utils.NetworkResult
import com.torresj.newathletic.utils.ProgressBarGenerico
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
            ProgressBarGenerico.LoadProgress(this)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                if (binding.eMail.text.toString().isEmpty()) {
                    MensajesGenericos.SHowMensajesGenericos(
                        "Error",
                        "¡Error!",
                        "Debes ingresar tu usuario",
                        this@LoginActivity
                    )
                    ProgressBarGenerico.HideProgreess()
                } else if (binding.passwords.text.toString().isEmpty()) {
                    MensajesGenericos.SHowMensajesGenericos(
                        "Error",
                        "¡Error!",
                        "Debes ingresar tu contraseña",
                        this@LoginActivity
                    )
                    ProgressBarGenerico.HideProgreess()
                } else {
                    usuario.usuario = binding.eMail.text.toString()
                    usuario.clave = binding.passwords.text.toString()
                    usuarioViewModel.ingresar(usuario)
                    bindObservers()
                    ProgressBarGenerico.HideProgreess()
                }
            }
        }
    }


    private fun bindObservers() {
        usuarioViewModel.userResponseLiveData.observe(this, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    tokenManager.saveToken(it.data?.token!!)
                    startActivity(Intent(this@LoginActivity, PreferenciasActivity::class.java))
                }
                is NetworkResult.Error -> {
                    ProgressBarGenerico.HideProgreess()
                    MensajesGenericos.SHowMensajesGenericos(
                        "Error",
                        "¡Error!",
                        it.message.toString(),
                        this@LoginActivity
                    )
                }
                is NetworkResult.Loading -> {
                    ProgressBarGenerico.LoadProgress(this)
                }
            }
        })
    }

}