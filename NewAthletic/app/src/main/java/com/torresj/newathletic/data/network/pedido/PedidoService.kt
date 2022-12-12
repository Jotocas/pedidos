package com.torresj.newathletic.data.network.pedido

import com.torresj.newathletic.data.model.DtoCoPedido
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PedidoService @Inject constructor(private val api:PedidoApiClient) {

        suspend fun listarPedidosPaginacion(): List<DtoCoPedido> {
            return withContext(Dispatchers.IO) {
                val response = api.listarPedidosPaginacion()
                response.body() ?: emptyList()
            }
        }
}