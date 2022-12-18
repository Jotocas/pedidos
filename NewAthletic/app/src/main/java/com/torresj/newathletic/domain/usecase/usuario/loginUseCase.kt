package com.torresj.newathletic.domain.usecase.usuario

import androidx.lifecycle.LiveData
import com.torresj.newathletic.data.PedidoRepository
import com.torresj.newathletic.data.UsuarioRepository
import com.torresj.newathletic.data.model.DtoCoPedido
import com.torresj.newathletic.data.model.SeguridadUsuarioLogin
import com.torresj.newathletic.utils.NetworkResult
import javax.inject.Inject

class loginUseCase  @Inject constructor(private val repository: UsuarioRepository) {

    val userResponseLiveData: LiveData<NetworkResult<SeguridadUsuarioLogin>>
        get() = repository.userResponseLiveData

    suspend operator fun invoke(usuario:SeguridadUsuarioLogin): Unit? {
        return repository.ingresar(usuario)
    }
}