package com.torresj.newathletic.data.network.pedido

import com.torresj.newathletic.data.model.DtoCoPedido
import retrofit2.Response
import retrofit2.http.POST

interface PedidoApiClient {
    @POST()
    suspend fun listarPedidosPaginacion(): Response<List<DtoCoPedido>>


}