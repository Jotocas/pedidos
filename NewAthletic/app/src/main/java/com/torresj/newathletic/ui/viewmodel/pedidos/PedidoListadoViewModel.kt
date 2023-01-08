package com.torresj.newathletic.ui.viewmodel.pedidos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.torresj.newathletic.data.model.DtoCoPedido
import com.torresj.newathletic.data.model.FiltroCoPedido
import com.torresj.newathletic.data.model.SeguridadUsuarioLogin
import com.torresj.newathletic.domain.usecase.pedidos.obtenerPedidosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PedidoListadoViewModel @Inject constructor(
    private val getObtenerPedidosUseCase: obtenerPedidosUseCase
) : ViewModel() {

    val listaPedidos = MutableLiveData<List<DtoCoPedido>>()

    fun listarPedidos(filtro: FiltroCoPedido) {
        viewModelScope.launch {
            val result = getObtenerPedidosUseCase(filtro)
            if (!result.isNullOrEmpty()) {
                listaPedidos.postValue(result)
            }
        }
    }

}