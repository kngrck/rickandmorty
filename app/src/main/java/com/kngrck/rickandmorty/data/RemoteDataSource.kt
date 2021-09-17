package com.kngrck.rickandmorty.data

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getCharacters(page: Int) = apiService.getCharacters(page)

    suspend fun getCharacterById(id: Int) = apiService.getCharacterById(id)
}