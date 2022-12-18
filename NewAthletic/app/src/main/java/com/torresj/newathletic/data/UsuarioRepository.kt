package com.torresj.newathletic.data

import androidx.lifecycle.LiveData
import com.torresj.newathletic.data.model.DtoCoPedido
import com.torresj.newathletic.data.model.SeguridadUsuarioLogin
import com.torresj.newathletic.data.network.pedido.PedidoService
import com.torresj.newathletic.data.network.usuario.UsuarioService
import com.torresj.newathletic.utils.NetworkResult
import retrofit2.Response
import javax.inject.Inject


class UsuarioRepository @Inject constructor(
    private val api: UsuarioService
){

    val userResponseLiveData: LiveData<NetworkResult<SeguridadUsuarioLogin>>
        get() = api.userResponseLiveData

    suspend fun ingresar(usuario:SeguridadUsuarioLogin?): Unit? {
        val response= usuario?.let { api.ingresar(it) }
        return response
    }

}