package com.torresj.newathletic.domain.usecase.preferencias

import androidx.lifecycle.LiveData
import com.torresj.newathletic.data.PreferenciasRepository
import com.torresj.newathletic.data.model.DtoComunSyPreferences
import com.torresj.newathletic.utils.NetworkResult
import javax.inject.Inject

class PreferenciasUseCase  @Inject constructor(private val repository: PreferenciasRepository) {

    val preferenciasResponseLiveData: LiveData<NetworkResult<DtoComunSyPreferences>>
        get() = repository.preferenciasResponseLiveData

    suspend operator fun invoke(): Unit? {
        return repository.obtenerPreferencias()
    }
}