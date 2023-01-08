package com.torresj.newathletic.di

import com.torresj.newathletic.data.network.clientes.ClientesApiClient
import com.torresj.newathletic.data.network.pedido.PedidoApiClient
import com.torresj.newathletic.data.network.preferencias.PreferenciasApiClient
import com.torresj.newathletic.data.network.usuario.UsuarioApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit.Builder {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder().baseUrl("http://10.0.2.2:8080")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun providesUserAPI(retrofitBuilder: Retrofit.Builder): UsuarioApiClient {
        return retrofitBuilder.build().create(UsuarioApiClient::class.java)
    }

    @Singleton
    @Provides
    fun providePreferenciasApiClient(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): PreferenciasApiClient {
        return retrofitBuilder.client(okHttpClient).build().create(PreferenciasApiClient::class.java)
    }

    @Singleton
    @Provides
    fun providePedidoApiClient(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): PedidoApiClient {
        return retrofitBuilder.client(okHttpClient).build().create(PedidoApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideClienteApiClient(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): ClientesApiClient {
        return retrofitBuilder.client(okHttpClient).build().create(ClientesApiClient::class.java)
    }
}