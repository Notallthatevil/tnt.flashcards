package com.trippntechnology.tnt.flashcards.ux.fragments.main.noteconfigurationlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.trippntechnology.tnt.androidbaseutils.ux.fragments.BaseFragment
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.databinding.FragmentNoteConfigurationListViewBinding
import com.trippntechnology.tnt.flashcards.injector.Injector
import com.trippntechnology.tnt.flashcards.ux.adapters.NoteConfigurationListAdapter
import com.vikingsen.inject.viewmodel.ViewModelFactory
import timber.log.Timber
import javax.inject.Inject

class NoteConfigurationListFragment : BaseFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: FragmentNoteConfigurationListViewBinding

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(NoteConfigurationListViewModel::class.java)
    }

    private val adapter by lazy { NoteConfigurationListAdapter(viewModel) }

    init {
        Injector.get().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //        return inflater.inflate(R.layout.fragment_note_configuration_list_view,container,false)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_note_configuration_list_view, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel = this@NoteConfigurationListFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
            recyclerViewSelectedNotesList.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewSelectedNotesList.adapter = this@NoteConfigurationListFragment.adapter
            recyclerViewSelectedNotesList.addItemDecoration(
                DividerItemDecoration(
                    requireContext(), DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun setUpObservers() {
        viewModel.openPacket.observeNotNull {
            Timber.d("Opening packet $it")
            val directions = NoteConfigurationListFragmentDirections.openPacket(it)
            findNavController().navigate(directions)
        }
        viewModel.loadConfig.observeNotNull {
            Timber.d("Loading config $it")
            val directions = NoteConfigurationListFragmentDirections.showNoteConfig(it)
            findNavController().navigate(directions)
        }
    }
}