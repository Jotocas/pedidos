package com.torresj.newathletic.data.network.usuario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.torresj.newathletic.data.model.SeguridadUsuarioLogin
import com.torresj.newathletic.data.network.ApiEndPoint
import com.torresj.newathletic.utils.NetworkResult
import com.torresj.newathletic.utils.UValidador
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject


class UsuarioService @Inject constructor(private val api: UsuarioApiClient) {

 private val _userResponseLiveData = MutableLiveData<NetworkResult<SeguridadUsuarioLogin>>()
    val userResponseLiveData: LiveData<NetworkResult<SeguridadUsuarioLogin>>
        get() = _userResponseLiveData

    suspend fun ingresar(userRequest: SeguridadUsuarioLogin) {
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response =api.ingresar(userRequest, ApiEndPoint.ENDPOINT_LOGIN)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<SeguridadUsuarioLogin>) {
        if (response.isSuccessful && response.body() != null) {
            if(UValidador.esListaVacia(response.body()!!.transaccionListaMensajes)){
                _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
            }else{
                _userResponseLiveData.postValue(NetworkResult.Error(response.body()!!.transaccionListaMensajes[0].mensaje))
            }
        }
        else if(response.errorBody()!=null){
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        }
        else{
            _userResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
}