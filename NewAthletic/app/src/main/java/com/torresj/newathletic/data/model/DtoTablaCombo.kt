package com.torresj.newathletic.data.model

data class DtoTablaCombo(
    val value:String,
    val label:String,
)
{
    override fun toString(): String {
        return label
    }
}

