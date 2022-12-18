package com.torresj.newathletic.data.network.preferencias

import com.torresj.newathletic.data.model.DtoComunSyPreferences
import com.torresj.newathletic.data.model.SeguridadUsuarioLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Url

interface PreferenciasApiClient {
    @PUT()
    suspend fun obtenerPreferencias(@Url authUrl: String): Response<DtoComunSyPreferences>


}