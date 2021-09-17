package com.kngrck.rickandmorty.ui.home

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kngrck.rickandmorty.R
import com.kngrck.rickandmorty.databinding.ItemCharacterBinding
import com.kngrck.rickandmorty.databinding.ItemCharacterFirstLetterBinding
import com.kngrck.rickandmorty.domain.model.Character
import com.kngrck.rickandmorty.utils.ViewBindingEpoxyModel
import kotlinx.coroutines.ObsoleteCoroutinesApi
import java.util.*

@ObsoleteCoroutinesApi
class CharactersEpoxyController(
    private val onCharacterSelected: (Int) -> Unit
) : PagingDataEpoxyController<Character>() {
    override fun buildItemModel(currentPosition: Int, item: Character?): EpoxyModel<*> {

        return CharacterEpoxyModel(
            character = item!!,
            onClick = onCharacterSelected
        ).id("character_${item.id}")

    }


    override fun addModels(models: List<EpoxyModel<*>>) {

        CharacterFirstLetterEpoxyModel("Main Family")
            .id("main_family_header")
            .addTo(this)

        super.addModels(models.subList(0, 5))

        (models.subList(5, models.size) as List<CharacterEpoxyModel>).groupBy {
            it.character.name[0].uppercaseChar()
        }.forEach { letter ->
            val firstLetter = letter.key.toString().uppercase(Locale.US)
            CharacterFirstLetterEpoxyModel(firstLetter)
                .id("letter_${letter}")
                .addTo(this)

            super.addModels(letter.value)
        }
    }

    data class CharacterEpoxyModel(
        val character: Character,
        val onClick: (Int) -> Unit
    ) : ViewBindingEpoxyModel<ItemCharacterBinding>(R.layout.item_character) {
        override fun ItemCharacterBinding.bind() {
            val options = RequestOptions().placeholder(R.drawable.ic_launcher_background)
            Glide.with(characterImageView.context)
                .applyDefaultRequestOptions(options)
                .load(character.image).into(characterImageView)

            characterNameTextView.text = character.name

            root.setOnClickListener { onClick(character.id) }
        }

    }

    data class CharacterFirstLetterEpoxyModel(
        val letter: String,
    ) : ViewBindingEpoxyModel<ItemCharacterFirstLetterBinding>(R.layout.item_character_first_letter) {
        override fun ItemCharacterFirstLetterBinding.bind() {

            letterTextView.text = letter
        }

        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            return totalSpanCount
        }

    }
}