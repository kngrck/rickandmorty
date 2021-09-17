package com.kngrck.rickandmorty.data

import com.kngrck.rickandmorty.data.entity.CharacterResponse
import com.kngrck.rickandmorty.data.entity.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharactersResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<CharacterResponse>
}