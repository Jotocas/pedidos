package com.torresj.newathletic.data

import com.torresj.newathletic.data.model.DtoCoPedido
import com.torresj.newathletic.data.model.FiltroCoPedido
import com.torresj.newathletic.data.network.pedido.PedidoService
import javax.inject.Inject


class PedidoRepository @Inject constructor(
    private val api: PedidoService
){
    suspend fun listarPedidosPaginacion(filtro: FiltroCoPedido):List<DtoCoPedido>{
        val response: List<DtoCoPedido> = api.listarPedidosPaginacion(filtro)
        return response.map { it }
    }
}