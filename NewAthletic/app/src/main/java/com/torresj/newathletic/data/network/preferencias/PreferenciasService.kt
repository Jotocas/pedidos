package com.torresj.newathletic.data.network.preferencias

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.torresj.newathletic.data.model.DtoComunSyPreferences
import com.torresj.newathletic.data.network.ApiEndPoint
import com.torresj.newathletic.utils.NetworkResult
import com.torresj.newathletic.utils.UValidador
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject


class PreferenciasService @Inject constructor(private val api: PreferenciasApiClient) {

    private val _preferenciasResponseLiveData =
        MutableLiveData<NetworkResult<DtoComunSyPreferences>>()

    private val _preferenciasActResponseLiveData =
        MutableLiveData<NetworkResult<DtoComunSyPreferences>>()

    val preferenciasResponseLiveData: LiveData<NetworkResult<DtoComunSyPreferences>>
        get() = _preferenciasResponseLiveData

    val preferenciasActResponseLiveData: LiveData<NetworkResult<DtoComunSyPreferences>>
        get() = _preferenciasActResponseLiveData

    suspend fun obtenerPreferencias() {
        _preferenciasResponseLiveData.postValue(NetworkResult.Loading())
        val response = api.obtenerPreferencias(ApiEndPoint.ENDPOINT_OBTENER_PREFERENCIAS)
        handleResponse(response)
    }

    suspend fun actualizarPreferencias(bean: DtoComunSyPreferences) {
        _preferenciasActResponseLiveData.postValue(NetworkResult.Loading())
        val response =
            api.actualizarPreferencias(bean, ApiEndPoint.ENDPOINT_ACTUALIZAR_PREFERENCIAS)
        handleResponse(response)
    }

    suspend fun guardarPreferencias(bean: DtoComunSyPreferences) {
        _preferenciasResponseLiveData.postValue(NetworkResult.Loading())
        val response = api.guardarPreferencias(bean, ApiEndPoint.ENDPOINT_GUARDAR_PREFERENCIAS)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<DtoComunSyPreferences>) {
        if (response.isSuccessful && response.body() != null) {
            if (UValidador.esListaVacia(response.body()!!.transaccionListaMensajes)) {
                _preferenciasResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
                _preferenciasActResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
            } else {
                _preferenciasResponseLiveData.postValue(NetworkResult.Error(response.body()!!.transaccionListaMensajes[0].mensaje))
                _preferenciasActResponseLiveData.postValue(NetworkResult.Error(response.body()!!.transaccionListaMensajes[0].mensaje))
            }
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _preferenciasResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            _preferenciasActResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _preferenciasResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            _preferenciasActResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
}