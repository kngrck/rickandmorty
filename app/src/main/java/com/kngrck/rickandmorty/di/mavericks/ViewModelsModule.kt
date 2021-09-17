package com.kngrck.rickandmorty.di.mavericks

import com.kngrck.rickandmorty.ui.character.CharacterViewModel
import com.kngrck.rickandmorty.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(CharacterViewModel::class)
    fun characterViewModelFactory(factory: CharacterViewModel.Factory): AssistedViewModelFactory<*, *>

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun homeViewModelFactory(factory: HomeViewModel.Factory): AssistedViewModelFactory<*, *>
}



