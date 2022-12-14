package com.torresj.newathletic.ui.view.usuario

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.torresj.newathletic.data.model.DtoComunSyPreferences
import com.torresj.newathletic.data.model.DtoTablaCombo
import com.torresj.newathletic.databinding.ActivityPreferenciasBinding
import com.torresj.newathletic.ui.view.MainActivity
import com.torresj.newathletic.ui.viewmodel.usuario.PreferenciasViewModel
import com.torresj.newathletic.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreferenciasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreferenciasBinding
    private val preferenciasViewModel: PreferenciasViewModel by viewModels()
    private lateinit var preferencias: DtoComunSyPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferenciasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ProgressBarGenerico.LoadProgress(this)
        preferenciasViewModel.obtenerPreferencias()
        bindObservers()

        binding.fabIcon.setOnClickListener { view ->
            if (!validar()) {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    private fun validar(): Boolean {
        var validado = false
        if (UString.esNuloVacio(preferencias.seriefac)) {
            MensajesGenericos.SHowMensajesGenericos(
                "Error",
                "¡Error!",
                "Seleccione una Serie de Factura",
                this
            )

            validado = true
        }

        if (UString.esNuloVacio(preferencias.seriebol)) {
            MensajesGenericos.SHowMensajesGenericos(
                "Error",
                "¡Error!",
                "Seleccione una Serie de Boleta",
                this
            )

            validado = true
        }

        return validado
    }

    private fun guardarPreferencias() {

    }

    private fun obtenerPreferencias() {
        preferenciasViewModel.actualizarPreferencias(preferencias)
        bindObserversSeries()
    }

    private fun bindObserversSeries() {
        var preferenciasTemp: DtoComunSyPreferences
        preferenciasViewModel.preferenciasActResponseLiveData.observe(this, Observer {

            when (it) {
                is NetworkResult.Success -> {
                    preferenciasTemp = it.data!!
                    ProgressBarGenerico.HideProgreess()
                    setBoletas(preferenciasTemp)
                    setFacturas(preferenciasTemp)
                }
                is NetworkResult.Error -> {
                    ProgressBarGenerico.HideProgreess()
                    MensajesGenericos.SHowMensajesGenericos(
                        "Error",
                        "¡Error!",
                        it.message.toString(),
                        this
                    )
                }
                is NetworkResult.Loading -> {
                    //ProgressBarGenerico.LoadProgress(this)
                }
            }
        })
    }

    private fun bindObservers() {
        preferenciasViewModel.preferenciasResponseLiveData.observe(this, Observer {

            when (it) {
                is NetworkResult.Success -> {
                    preferencias = it.data!!
                    ProgressBarGenerico.HideProgreess()
                    binding.txtCompania.text = preferencias.companiasocio
                    setEstablecimiento(preferencias)
                    setBoletas(preferencias)
                    setFacturas(preferencias)
                }
                is NetworkResult.Error -> {
                    ProgressBarGenerico.HideProgreess()
                    MensajesGenericos.SHowMensajesGenericos(
                        "Error",
                        "¡Error!",
                        it.message.toString(),
                        this
                    )
                }
                is NetworkResult.Loading -> {
                    //ProgressBarGenerico.LoadProgress(this)
                }
            }
        })
    }

    private fun setEstablecimiento(preferencia: DtoComunSyPreferences) {
        var lstMaestro: MutableList<DtoTablaCombo?> = ArrayList<DtoTablaCombo?>()
        lstMaestro = ArrayList<DtoTablaCombo?>()
        lstMaestro.add(DtoTablaCombo("", "-- Seleccione --"))

        if (!UValidador.esListaVacia(preferencia.dwc_establecimiento)) {
            preferencia.dwc_establecimiento!!.forEach { estable ->
                lstMaestro.add(DtoTablaCombo(estable.establecimiento!!, estable.descripcionlocal!!))
            }
        }

        val adapterspinner: ArrayAdapter<DtoTablaCombo> =
            ArrayAdapter<DtoTablaCombo>(this, R.layout.simple_spinner_item, lstMaestro)

        adapterspinner.setDropDownViewResource(R.layout.simple_dropdown_item_1line)
        binding.spnEstablecimiento.adapter = adapterspinner

        /* val selection = DtoTablaCombo(preferencia.establecimiento!!,"")
         val spinnerPosition: Int = adapterspinner.getPosition(selection)
         binding.spnEstablecimiento.setSelection(spinnerPosition)
                 */

        binding.spnEstablecimiento.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    // You can define your actions as you want
                }

                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {

                    val selectedObject = binding.spnEstablecimiento.selectedItem as DtoTablaCombo
                    preferencias.establecimiento = selectedObject.value

                    obtenerPreferencias()

                }
            }
    }

    private fun setFacturas(preferencia: DtoComunSyPreferences) {
        var lstMaestro: MutableList<DtoTablaCombo?> = ArrayList<DtoTablaCombo?>()
        lstMaestro = ArrayList<DtoTablaCombo?>()
        lstMaestro.add(DtoTablaCombo("", "-- Seleccione --"))

        if (!UValidador.esListaVacia(preferencia.dwc_seriefac)) {
            preferencia.dwc_seriefac!!.forEach { estable ->
                lstMaestro.add(DtoTablaCombo(estable.numeroserie!!, estable.numeroserie!!))
            }
        }

        val adapterspinner: ArrayAdapter<DtoTablaCombo> =
            ArrayAdapter<DtoTablaCombo>(this, R.layout.simple_spinner_item, lstMaestro)

        adapterspinner.setDropDownViewResource(R.layout.simple_dropdown_item_1line)
        binding.spnfacturas.adapter = adapterspinner

        val selection = DtoTablaCombo(preferencia.seriefac!!, preferencia.seriefac!!)
        val spinnerPosition: Int = adapterspinner.getPosition(selection)
        binding.spnfacturas.setSelection(spinnerPosition)

        binding.spnfacturas.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    // You can define your actions as you want
                }

                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {

                    val selectedObject = binding.spnfacturas.selectedItem as DtoTablaCombo
                    preferencias.seriefac = selectedObject.value

                }
            }

    }

    private fun setBoletas(preferencia: DtoComunSyPreferences) {
        var lstMaestro: MutableList<DtoTablaCombo?> = ArrayList<DtoTablaCombo?>()
        lstMaestro = ArrayList<DtoTablaCombo?>()
        lstMaestro.add(DtoTablaCombo("", "-- Seleccione --"))

        if (!UValidador.esListaVacia(preferencia.dwc_seriebol)) {
            preferencia.dwc_seriebol!!.forEach { estable ->
                lstMaestro.add(DtoTablaCombo(estable.numeroserie!!, estable.numeroserie!!))
            }
        }

        val adapterspinner: ArrayAdapter<DtoTablaCombo> =
            ArrayAdapter<DtoTablaCombo>(this, R.layout.simple_spinner_item, lstMaestro)

        adapterspinner.setDropDownViewResource(R.layout.simple_dropdown_item_1line)
        binding.spnboletas.adapter = adapterspinner

        binding.spnboletas.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    // You can define your actions as you want
                }

                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {

                    val selectedObject = binding.spnboletas.selectedItem as DtoTablaCombo
                    preferencias.seriebol = selectedObject.value

                }
            }
    }
}