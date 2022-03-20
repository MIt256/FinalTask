package com.example.finaltask.models

data class Field(
    val name: String,
    val title: String,
    val type: String,
    val values: Map<String,String>?
)