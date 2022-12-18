package com.torresj.newathletic.data.model

import java.math.BigDecimal
import java.util.*

data class DtoCoPedido(
    val numeroDocumento: String,
    val estadoNombre: String? = null,
    val clienteNombre: String,
    val montoTotal: BigDecimal? = null,
    val fechaDocumento: Date? = null
){
    private val iv_action: String? = null
    private val companiaSocio: String? = null
    private val tipoDocumento: String? = null
    private val estado: String? = null
    private val eswebflag: String? = null
    private val monedaDocumento: String? = null
    private val sucursalNombre: String? = null
    private val clienteNumero: Int? = null
    private val comercialPedidoFechaRequerida: Date? = null
    private val fechaAprobacion: Date? = null
    private val comentarios: String? = null
    private val sucursal: String? = null
    private val comercialPedidoNumero: String? = null
    private val unidadNegocio: String? = null
    private val formaFacturacion: String? = null
    private val impresionPendienteFlag: String? = null
    private val tipoVenta: String? = null
    private val prioridad: String? = null
    private val centroCosto: String? = null
    private val almacenCodigo: String? = null
    private val voBoComercialFlag: String? = null
    private val nomEstado: String? = null
    private val nombreMonedaDocumento: String? = null
    private val vendedorNombre: String? = null
    private val concepto: String? = null
    private val contador: Int? = null
   // private val filas: BigInteger? = null
    private val preparadoPor: String? = null
    private val entransporte: String? = null

    private val numerointerno: String? = null
    private val establecimientocodigo: String? = null
    private val clientedirecciondespacho: Int? = null
    private val fechaEntrega: Date? = null
    private val turnoEntrega: Int? = null

   // private val listaErrores: List<DominioMensajeUsuario>? = null

    private val tiporegistro: String? = null
    private val impuesto: String? = null
    private val porcentaje: BigDecimal? = null
    private val monto: BigDecimal? = null
    private val sumatoriaTotal: BigDecimal? = null
    private val seriedocumento: String? = null
    private val tipodocumentoventa: String? = null
}
