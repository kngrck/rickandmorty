package com.kngrck.rickandmorty.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.airbnb.mvrx.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kngrck.rickandmorty.R
import com.kngrck.rickandmorty.databinding.FragmentCharacterBinding
import com.kngrck.rickandmorty.domain.model.Character
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment(), MavericksView {
    private lateinit var _binding: FragmentCharacterBinding
    private val viewModel: CharacterViewModel by fragmentViewModel()
    private val args: CharacterFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCharacterById(args.characterId)
    }

    override fun invalidate() {
        withState(viewModel) {
            with(_binding){
                when (it.character) {
                    // 1
                    is Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    // 2
                    is Success -> {
                        progressBar.visibility = View.GONE

                        updateUI(it.character.invoke())
                    }
                    else -> {
                        progressBar.visibility = View.GONE

                        Toast.makeText(
                            requireContext(),
                            "Failed to load character",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun updateUI(character: Character) {
        val epoxyController = CharacterEpoxyController()

        epoxyController.character = character
        _binding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }
}