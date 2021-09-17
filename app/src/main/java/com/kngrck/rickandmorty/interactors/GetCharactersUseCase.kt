package com.kngrck.rickandmorty.interactors

import com.kngrck.rickandmorty.domain.repository.ApiRepository
import javax.inject.Inject


class GetCharactersUseCase @Inject constructor(private val apiRepository: ApiRepository) {

    suspend operator fun invoke(page: Int) = apiRepository.getCharacters(page)

}