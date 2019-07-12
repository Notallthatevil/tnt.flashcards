package com.trippntechnology.tnt.flashcards.ux.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.databinding.FragmentEnabledNotesListViewBinding
import com.trippntechnology.tnt.flashcards.injector.Injector
import com.trippntechnology.tnt.flashcards.util.fragments.BaseFragment
import com.trippntechnology.tnt.flashcards.ux.activities.main.MainViewModel
import com.trippntechnology.tnt.flashcards.ux.adapters.EnabledNotesListAdapter
import com.vikingsen.inject.viewmodel.ViewModelFactory
import javax.inject.Inject

class EnabledNotesListFragment : BaseFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: FragmentEnabledNotesListViewBinding

    private val viewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(MainViewModel::class.java)
    }

    private val adapter by lazy { EnabledNotesListAdapter(viewModel) }

    init {
        Injector.get().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_enabled_notes_list_view, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel = this@EnabledNotesListFragment.viewModel
            lifecycleOwner = this@EnabledNotesListFragment
            recyclerViewSelectedNotesList.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewSelectedNotesList.adapter = this@EnabledNotesListFragment.adapter
            recyclerViewSelectedNotesList.addItemDecoration(
                DividerItemDecoration(
                    requireContext(), DividerItemDecoration.VERTICAL
                )
            )
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUpObservers() {
        viewModel.mainViewModelEvent.observe {
            when (it!!) {
                MainViewModel.EVENT_NEW_ENABLED_NOTES_CONFIG -> {
                    NavHostFragment.findNavController(this).navigate(R.id.selectNoteFragment)
                }
                MainViewModel.EVENT_LOAD_CONFIG -> {
                    NavHostFragment.findNavController(this).navigate(R.id.selectNoteFragment)
                }
            }
        }
    }

}