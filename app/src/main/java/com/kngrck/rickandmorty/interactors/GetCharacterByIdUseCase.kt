package com.kngrck.rickandmorty.interactors

import com.kngrck.rickandmorty.domain.repository.ApiRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(private val apiRepository: ApiRepository) {

    suspend operator fun invoke(id: Int) = apiRepository.getCharacterById(id)
}