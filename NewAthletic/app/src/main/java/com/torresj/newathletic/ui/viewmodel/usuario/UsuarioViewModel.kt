package com.torresj.newathletic.ui.viewmodel.usuario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.torresj.newathletic.data.model.SeguridadUsuarioLogin
import com.torresj.newathletic.domain.usecase.usuario.loginUseCase
import com.torresj.newathletic.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsuarioViewModel @Inject constructor(
    private val getloginUseCase: loginUseCase
) : ViewModel() {

    val userResponseLiveData: LiveData<NetworkResult<SeguridadUsuarioLogin>>
        get() = getloginUseCase.userResponseLiveData

    val usuario = MutableLiveData<SeguridadUsuarioLogin?>()

       fun ingresar(bean:SeguridadUsuarioLogin) {
        viewModelScope.launch {
             val result = getloginUseCase(bean)
                usuario.postValue(result)
         }
    }
}

private fun <T> MutableLiveData<T>.postValue(result: Unit?) {

}


