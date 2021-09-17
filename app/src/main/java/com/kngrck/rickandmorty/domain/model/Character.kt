package com.kngrck.rickandmorty.domain.model

data class Character(
    val id: Int,
    val image: String,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val firstLetter: String
)
