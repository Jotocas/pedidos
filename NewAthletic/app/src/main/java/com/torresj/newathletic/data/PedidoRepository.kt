package com.torresj.newathletic.data

import com.torresj.newathletic.data.model.DtoCoPedido
import com.torresj.newathletic.data.network.pedido.PedidoService
import javax.inject.Inject


class PedidoRepository @Inject constructor(
    private val api: PedidoService
){
    suspend fun listarPedidosPaginacion():List<DtoCoPedido>{
        val response: List<DtoCoPedido> = api.listarPedidosPaginacion()
        return response.map { it }
    }
}