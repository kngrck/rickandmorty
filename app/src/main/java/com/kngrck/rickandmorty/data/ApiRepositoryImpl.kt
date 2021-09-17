package com.kngrck.rickandmorty.data

import com.kngrck.rickandmorty.data.entity.CharacterResponse
import com.kngrck.rickandmorty.data.entity.CharactersResponse
import com.kngrck.rickandmorty.domain.repository.ApiRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ApiRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ApiRepository {

    override suspend fun getCharacters(page: Int): Response<CharactersResponse> {
        return remoteDataSource.getCharacters(page)
    }

    override suspend fun getCharacterById(id: Int): Response<CharacterResponse> {
        return remoteDataSource.getCharacterById(id)
    }


}