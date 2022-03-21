package com.example.finaltask.data.models

data class Field(
    val name: String,
    val title: String,
    val type: String,
    val values: Map<String,String>?
){
    var value:String=""
}