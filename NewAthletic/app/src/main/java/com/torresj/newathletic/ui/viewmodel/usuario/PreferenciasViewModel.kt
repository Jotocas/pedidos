package com.torresj.newathletic.ui.viewmodel.usuario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.torresj.newathletic.data.model.DtoComunSyPreferences
import com.torresj.newathletic.data.model.SeguridadUsuarioLogin
import com.torresj.newathletic.domain.usecase.preferencias.PreferenciasUseCase
import com.torresj.newathletic.domain.usecase.usuario.loginUseCase
import com.torresj.newathletic.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferenciasViewModel @Inject constructor(
    private val getPreferenciasUseCase: PreferenciasUseCase
) : ViewModel() {

    val preferenciasResponseLiveData: LiveData<NetworkResult<DtoComunSyPreferences>>
        get() = getPreferenciasUseCase.preferenciasResponseLiveData

    val preferencia = MutableLiveData<DtoComunSyPreferences?>()

       fun obtenerPreferencias() {
        viewModelScope.launch {
             val result = getPreferenciasUseCase()
            preferencia.postValue(result)
         }
    }
}

private fun <T> MutableLiveData<T>.postValue(result: Unit?) {

}


