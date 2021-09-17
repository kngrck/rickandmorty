package com.kngrck.rickandmorty.ui.character.model

import com.kngrck.rickandmorty.R
import com.kngrck.rickandmorty.databinding.ModelCharacterInfoBinding
import com.kngrck.rickandmorty.utils.ViewBindingEpoxyModel

class CharacterInfoEpoxyModel(
    private val name: String,
    private val status: String,
    private val species: String,
    private val type: String,

    ) : ViewBindingEpoxyModel<ModelCharacterInfoBinding>(R.layout.model_character_info) {

    override fun ModelCharacterInfoBinding.bind() {
        characterNameTextView.text = name
        characterStatusTextView.text = status
        characterSpeciesTextView.text = species
    }
}