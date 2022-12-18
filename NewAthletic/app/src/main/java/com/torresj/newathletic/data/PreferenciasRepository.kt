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

    suspend fun obtenerPreferencias(): Unit? {
        val response= api.obtenerPreferencias()
        return response
    }

}