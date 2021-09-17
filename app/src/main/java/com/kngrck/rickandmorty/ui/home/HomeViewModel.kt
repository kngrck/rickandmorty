package com.kngrck.rickandmorty.ui.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.Success
import com.kngrck.rickandmorty.data.CharactersPagingSource
import com.kngrck.rickandmorty.di.mavericks.AssistedViewModelFactory
import com.kngrck.rickandmorty.di.mavericks.hiltMavericksViewModelFactory
import com.kngrck.rickandmorty.interactors.GetCharactersUseCase
import com.kngrck.rickandmorty.utils.Constants
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel @AssistedInject constructor(
    @Assisted initialState: HomeState,
    private val getCharactersUseCase: GetCharactersUseCase
) : MavericksViewModel<HomeState>(initialState) {



    val flow = Pager(
        PagingConfig(Constants.PAGE_SIZE, Constants.PREFETCH_DISTANCE, enablePlaceholders = false)
    ) {
        CharactersPagingSource(getCharactersUseCase)
    }.flow
        .cachedIn(viewModelScope)


    @AssistedFactory
    interface Factory : AssistedViewModelFactory<HomeViewModel, HomeState> {
        override fun create(state: HomeState): HomeViewModel
    }

    companion object :
        MavericksViewModelFactory<HomeViewModel, HomeState> by hiltMavericksViewModelFactory()
}