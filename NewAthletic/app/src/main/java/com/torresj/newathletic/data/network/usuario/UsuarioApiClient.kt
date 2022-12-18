package com.torresj.newathletic.data.network.usuario

import com.torresj.newathletic.data.model.SeguridadUsuarioLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Url

interface UsuarioApiClient {
    @PUT()
    suspend fun ingresar(@Body bean: SeguridadUsuarioLogin, @Url authUrl: String): Response<SeguridadUsuarioLogin>


}