package com.trippntechnology.tnt.flashcards.ux.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.databinding.FragmentSelectNoteBinding
import com.trippntechnology.tnt.flashcards.injector.Injector
import com.trippntechnology.tnt.flashcards.util.fragments.BaseFragment
import com.trippntechnology.tnt.flashcards.ux.activities.main.MainViewModel
import com.vikingsen.inject.viewmodel.ViewModelFactory
import javax.inject.Inject

class SelectNoteFragment : BaseFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(
            MainViewModel::class.java
        )
    }
    private lateinit var binding: FragmentSelectNoteBinding

    init {
        Injector.get().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_note, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel = this@SelectNoteFragment.viewModel
            lifecycleOwner = this@SelectNoteFragment
            selectionView.loadConfig(viewModel?.loadedConfig)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUpObservers() {
        viewModel.mainViewModelEvent.observe {
            when (it!!) {
                MainViewModel.EVENT_SAVE_CONFIG -> {
                    val noteList = binding.selectionView.exportConfig()
                    val name = binding.selectNoteConfigName.text.toString()
                    when {
                        name.isEmpty() -> Snackbar.make(
                            binding.root,
                            requireActivity().resources.getString(R.string.config_name_required),
                            Snackbar.LENGTH_LONG
                        ).show()
                        noteList.isEmpty() -> Snackbar.make(
                            binding.root,
                            requireActivity().resources.getString(R.string.config_selection_required),
                            Snackbar.LENGTH_LONG
                        ).show()
                        else -> {
                            var id: Long = 0
                            if (viewModel.loadedConfig != null) {
                                id = viewModel.loadedConfig!!.id
                            }
                            viewModel.saveNoteConfig(id, name, noteList)
                        }
                    }
                }
                MainViewModel.EVENT_CANCEL_CONFIG -> {
                    NavHostFragment.findNavController(this).navigate(R.id.enabledNotesListFragment)
                }
                MainViewModel.EVENT_CONFIG_SAVED -> {
                    NavHostFragment.findNavController(this).navigate(R.id.enabledNotesListFragment)
                }
            }
        }
    }
}