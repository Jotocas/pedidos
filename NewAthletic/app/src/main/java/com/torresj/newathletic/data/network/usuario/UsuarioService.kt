package com.torresj.newathletic.data.network.usuario

import com.torresj.newathletic.data.model.SeguridadUsuarioLogin
import com.torresj.newathletic.data.network.ApiEndPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UsuarioService @Inject constructor(private val api:UsuarioApiClient) {
        suspend fun ingresar(usuario:SeguridadUsuarioLogin): SeguridadUsuarioLogin? {
            return withContext(Dispatchers.IO) {
                val response = api.ingresar(usuario, ApiEndPoint.ENDPOINT_LOGIN)
                response.body()
            }
        }
}