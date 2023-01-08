package com.torresj.newathletic.data

import androidx.lifecycle.LiveData
import com.torresj.newathletic.data.model.DtoComunSyPreferences
import com.torresj.newathletic.data.network.preferencias.PreferenciasService
import com.torresj.newathletic.utils.NetworkResult
import javax.inject.Inject


class PreferenciasRepository @Inject constructor(
    private val api: PreferenciasService
){

    val preferenciasResponseLiveData: LiveData<NetworkResult<DtoComunSyPreferences>>
        get() = api.preferenciasResponseLiveData

    val preferenciasActResponseLiveData: LiveData<NetworkResult<DtoComunSyPreferences>>
        get() = api.preferenciasActResponseLiveData

    suspend fun obtenerPreferencias(): Unit? {
        val response= api.obtenerPreferencias()
        return response
    }

    suspend fun guardarPreferencias(bean:DtoComunSyPreferences): Unit? {
        val response= api.guardarPreferencias(bean)
        return response
    }

    suspend fun actualizarPreferencias(bean:DtoComunSyPreferences): Unit? {
        val response= api.actualizarPreferencias(bean)
        return response
    }
}