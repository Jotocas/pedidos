package com.torresj.newathletic.domain.usecase.usuario

import com.torresj.newathletic.data.PedidoRepository
import com.torresj.newathletic.data.UsuarioRepository
import com.torresj.newathletic.data.model.DtoCoPedido
import com.torresj.newathletic.data.model.SeguridadUsuarioLogin
import javax.inject.Inject

class loginUseCase  @Inject constructor(private val repository: UsuarioRepository) {
    suspend operator fun invoke(usuario:SeguridadUsuarioLogin): SeguridadUsuarioLogin? {
        return repository.ingresar(usuario)
    }
}