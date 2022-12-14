package com.torresj.newathletic.data.network

object ApiEndPoint {

    val API_SEGURIDAD="http://10.0.2.2:8080"
    val API_COMERCIAL="http://10.0.2.2:8086"
    val API_COMUN="http://10.0.2.2:8081"

    val ENDPOINT_LOGIN = API_SEGURIDAD + "/autorizacion/seguridad/login/ingresar"
    val ENDPOINT_LISTAR_PEDIDOS=API_COMERCIAL + "/spring/comercial/codocumento/listarPedidosPaginacion"
}