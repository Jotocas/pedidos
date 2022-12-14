package com.torresj.newathletic.domain.usecase.pedidos

import com.torresj.newathletic.data.PedidoRepository
import com.torresj.newathletic.data.model.DtoCoPedido
import com.torresj.newathletic.data.model.FiltroCoPedido
import javax.inject.Inject

class obtenerPedidosUseCase  @Inject constructor(private val repository: PedidoRepository) {
    suspend operator fun invoke(filtro: FiltroCoPedido):List<DtoCoPedido>{
        return repository.listarPedidosPaginacion(filtro)
    }
}