package com.torresj.newathletic.data.network.pedido

import com.torresj.newathletic.data.model.DtoCoPedido
import com.torresj.newathletic.data.model.FiltroCoPedido
import com.torresj.newathletic.data.network.ApiEndPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PedidoService @Inject constructor(private val api:PedidoApiClient) {

        suspend fun listarPedidosPaginacion(filtro: FiltroCoPedido): List<DtoCoPedido> {
            return withContext(Dispatchers.IO) {
                val response = api.listarPedidosPaginacion(filtro, ApiEndPoint.ENDPOINT_LISTAR_PEDIDOS)
                response.body() ?: emptyList()
            }
        }
}