package com.kngrck.rickandmorty.data.entity


data class CharactersResponse(
    val info: Info,
    val results: List<CharacterResponse>
)

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)

