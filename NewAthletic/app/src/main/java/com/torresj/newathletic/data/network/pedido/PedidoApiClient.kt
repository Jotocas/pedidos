package com.torresj.newathletic.data.network.pedido

import com.torresj.newathletic.data.model.DtoCoPedido
import com.torresj.newathletic.data.model.FiltroCoPedido
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface PedidoApiClient {
    @POST()
    suspend fun listarPedidosPaginacion(@Body filtro: FiltroCoPedido, @Url authUrl: String): Response<List<DtoCoPedido>>


}