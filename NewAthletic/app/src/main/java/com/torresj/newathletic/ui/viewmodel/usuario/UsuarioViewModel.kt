package com.torresj.newathletic.ui.viewmodel.usuario

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.torresj.newathletic.data.model.SeguridadUsuarioLogin
import com.torresj.newathletic.domain.usecase.usuario.loginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsuarioViewModel @Inject constructor(
    private val getloginUseCase: loginUseCase
) : ViewModel() {

    val usuario = MutableLiveData<SeguridadUsuarioLogin?>()

    fun ingresar(bean:SeguridadUsuarioLogin) {
        viewModelScope.launch {
           // isLoading.postValue(true)
            val result = getloginUseCase(bean)

           // if (!result.isNullOrEmpty()) {
                usuario.postValue(result)
               // isLoading.postValue(false)
          //  }
        }
    }
}