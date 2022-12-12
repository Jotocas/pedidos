package com.torresj.newathletic.di

import com.torresj.newathletic.data.network.pedido.PedidoApiClient
import com.torresj.newathletic.data.network.usuario.UsuarioApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun providePedidoApiClient(retrofit: Retrofit): PedidoApiClient {
        return retrofit.create(PedidoApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideUsuarioApiClient(retrofit: Retrofit): UsuarioApiClient {
        return retrofit.create(UsuarioApiClient::class.java)
    }
}