package com.kngrck.rickandmorty.domain.mappers

import com.kngrck.rickandmorty.data.entity.CharacterResponse
import com.kngrck.rickandmorty.domain.model.Character

object CharacterMapper {
    fun buildFrom(characterResponse: CharacterResponse): Character{
        return Character(
            id = characterResponse.id,
            name = characterResponse.name,
            image = characterResponse.image,
            status = characterResponse.status,
            species = characterResponse.species,
            type = characterResponse.type,
            firstLetter = firstLetterOfCharacterName(characterResponse.name)
        )
    }

    private fun firstLetterOfCharacterName(name: String) = name[0].toString()
}