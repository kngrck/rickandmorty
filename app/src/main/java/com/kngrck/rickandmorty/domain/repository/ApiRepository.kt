package com.kngrck.rickandmorty.domain.repository

import com.kngrck.rickandmorty.data.entity.CharacterResponse
import com.kngrck.rickandmorty.data.entity.CharactersResponse
import retrofit2.Response

interface ApiRepository {

    suspend fun getCharacters(page: Int): Response<CharactersResponse>

    suspend fun getCharacterById(id: Int): Response<CharacterResponse>
}