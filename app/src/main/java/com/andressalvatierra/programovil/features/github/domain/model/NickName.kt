package com.andressalvatierra.programovil.features.github.domain.model

@JvmInline
value class NickName(val value: String) {
    init{
        require(value.isNotEmpty()){
            "Nickname should not be empty"
        }
        require(value.length>3){
            "Nickname should have a minimun of 3 characters"
        }
    }
    override fun toString(): String = value
}