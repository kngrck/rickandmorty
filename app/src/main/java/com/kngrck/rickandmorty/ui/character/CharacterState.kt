package com.kngrck.rickandmorty.ui.character

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.kngrck.rickandmorty.domain.model.Character

data class CharacterState(
    val character: Async<Character> = Uninitialized
) : MavericksState