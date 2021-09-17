package com.kngrck.rickandmorty.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.kngrck.rickandmorty.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), MavericksView {
    private lateinit var _binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by fragmentViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val charactersEpoxyController = CharactersEpoxyController(::onCharacterSelected)

        lifecycleScope.launch {
            viewModel.flow.collectLatest {
                charactersEpoxyController.submitData(it)
            }
        }

        _binding.epoxyRecyclerView.setController(charactersEpoxyController)
    }

    override fun invalidate() {

    }

    private fun onCharacterSelected(characterId: Int) {
        val directions =
            HomeFragmentDirections.actionHomeFragmentToCharacterFragment(
                characterId = characterId
            )
        findNavController().navigate(directions)
    }



}