package com.kngrck.rickandmorty.ui.character

import com.airbnb.epoxy.EpoxyController
import com.kngrck.rickandmorty.ui.character.model.CharacterImageEpoxyModel
import com.kngrck.rickandmorty.ui.character.model.CharacterInfoEpoxyModel
import com.kngrck.rickandmorty.domain.model.Character

class CharacterEpoxyController: EpoxyController() {

    var character: Character? = null

    set(value) {
        field = value
        if(field!=null){
            requestModelBuild()
        }
    }

    override fun buildModels() {
        if (character == null) {
            // todo error state
            return
        }

        CharacterImageEpoxyModel(
            character!!.image
        ).id("image").addTo(this)

        CharacterInfoEpoxyModel(
            character!!.name,
            character!!.status,
            character!!.species,
            character!!.type
        ).id("info").addTo(this)

    }
}