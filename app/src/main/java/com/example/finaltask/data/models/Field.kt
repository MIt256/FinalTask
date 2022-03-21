package com.example.finaltask.data.models

import com.example.finaltask.constants.FieldType

data class Field(
    val name: String,
    val title: String,
    val type: FieldType,
    val values: Map<String,String>?
){
    var value:String=""
}