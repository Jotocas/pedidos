package com.torresj.newathletic.data

import com.torresj.newathletic.data.model.DtoCoPedido
import com.torresj.newathletic.data.model.SeguridadUsuarioLogin
import com.torresj.newathletic.data.network.pedido.PedidoService
import com.torresj.newathletic.data.network.usuario.UsuarioService
import javax.inject.Inject


class UsuarioRepository @Inject constructor(
    private val api: UsuarioService
){
    suspend fun ingresar(usuario:SeguridadUsuarioLogin?):SeguridadUsuarioLogin?{
        val response: SeguridadUsuarioLogin? = usuario?.let { api.ingresar(it) }
        return response
    }
}