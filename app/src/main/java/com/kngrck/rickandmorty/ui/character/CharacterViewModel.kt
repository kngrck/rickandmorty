package com.kngrck.rickandmorty.ui.character

import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.Success
import com.kngrck.rickandmorty.di.mavericks.AssistedViewModelFactory
import com.kngrck.rickandmorty.di.mavericks.hiltMavericksViewModelFactory
import com.kngrck.rickandmorty.domain.mappers.CharacterMapper
import com.kngrck.rickandmorty.interactors.GetCharacterByIdUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class CharacterViewModel @AssistedInject constructor(
    @Assisted initialState: CharacterState,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : MavericksViewModel<CharacterState>(initialState) {

    init {
        // 1
        setState {
            copy(character = Loading())
        }

    }

    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            val response = getCharacterByIdUseCase.invoke(id)

            if (!response.isSuccessful ) {
                throw Throwable("Error")
            }

            val character = CharacterMapper.buildFrom(response.body()!!)
            setState {
                copy(character = Success(character))
            }
        }

    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<CharacterViewModel, CharacterState> {
        override fun create(state: CharacterState): CharacterViewModel
    }

    companion object :
        MavericksViewModelFactory<CharacterViewModel, CharacterState> by hiltMavericksViewModelFactory()
}