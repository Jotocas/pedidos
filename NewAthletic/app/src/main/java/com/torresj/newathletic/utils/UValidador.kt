package com.torresj.newathletic.utils

object UValidador {
    fun esNulo(objeto: Any?): Boolean {
        return if (objeto == null) true else false
    }

    fun estaVacio(objeto: Any?): Boolean {
        return if (objeto != null) false else true
    }

    fun esListaVacia(lista: List<*>?): Boolean {
        return if (lista == null || lista.isEmpty()) true else false
    }
}