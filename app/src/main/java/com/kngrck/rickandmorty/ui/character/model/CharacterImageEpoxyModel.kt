package com.kngrck.rickandmorty.ui.character.model

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kngrck.rickandmorty.R
import com.kngrck.rickandmorty.databinding.ModelCharacterImageBinding
import com.kngrck.rickandmorty.utils.ViewBindingEpoxyModel

class CharacterImageEpoxyModel(
    private val image: String
) : ViewBindingEpoxyModel<ModelCharacterImageBinding>(R.layout.model_character_image) {

    override fun ModelCharacterImageBinding.bind() {
        val options =
            RequestOptions().placeholder(R.drawable.ic_launcher_background)
        Glide.with(characterImageView.context)
            .applyDefaultRequestOptions(options)
            .load(image).into(characterImageView)
    }
}