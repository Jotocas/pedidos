package com.torresj.newathletic.ui.viewmodel.pedidos

import androidx.lifecycle.ViewModel
import com.torresj.newathletic.domain.usecase.pedidos.obtenerPedidosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PedidoListadoViewModel @Inject constructor(
    private val getObtenerPedidosUseCase: obtenerPedidosUseCase
) : ViewModel() {


}