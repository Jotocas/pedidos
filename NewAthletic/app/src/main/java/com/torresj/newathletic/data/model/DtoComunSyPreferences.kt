package com.torresj.newathletic.data.model

import com.torresj.newathletic.core.DominioTransaccion
import java.math.BigDecimal
import java.util.*

data class DtoComunSyPreferences(
    val usuario: String,
    val preference: String
): DominioTransaccion(arrayListOf()) {

    val aplicacioncodigo: String? = null
    val tipovalor: String? = null
    val work: String? = null
    val valorstring: String? = null
    val valornumero: BigDecimal? = null
    val valorfecha: Date? = null
    val ultimousuario: String? = null
    val ultimafechamodif: Date? = null
    val descripcionlocal: String? = null
    val companiasocio: String? = null
    var establecimiento: String? = null
    val numeroserie: String? = null
    var seriefac: String? = null
    var seriebol: String? = null
    val serieguia: String? = null
    val serierecaudacion: String? = null
    val dwc_establecimiento: List<DtoComunSyPreferences>? = null
    val dwc_seriefac: List<DtoComunSyPreferences>? = null
    val dwc_seriebol: List<DtoComunSyPreferences>? = null
    val dwc_serieguia: List<DtoComunSyPreferences>? = null
    val dwc_serierecaudacion: List<DtoComunSyPreferences>? = null
}
