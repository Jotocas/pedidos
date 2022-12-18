package com.torresj.newathletic.data.model

import com.torresj.newathletic.core.DominioMensajeUsuario
import com.torresj.newathletic.core.DominioTransaccion

data class SeguridadUsuarioLogin(
    var usuario: String? = null,
    var clave: String? = null,
    val token: String? = null,
    ) : DominioTransaccion(arrayListOf()) {
    private val sid: String? = null
    private val tipoUsuarioId: String? = null
    private val aplicacionCodigo: String? = null
    private val aplicacionNombre: String? = null
    private val companiaCodigo: String? = null
    private val companiaNombre: String? = null

}
