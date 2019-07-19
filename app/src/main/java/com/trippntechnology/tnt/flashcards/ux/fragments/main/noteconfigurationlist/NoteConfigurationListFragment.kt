package com.trippntechnology.tnt.flashcards.ux.fragments.main.noteconfigurationlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.databinding.FragmentNoteConfigurationListViewBinding
import com.trippntechnology.tnt.flashcards.injector.Injector
import com.trippntechnology.tnt.flashcards.util.fragments.BaseFragment
import com.trippntechnology.tnt.flashcards.ux.adapters.NoteConfigurationListAdapter
import com.vikingsen.inject.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_note_configuration_list_view.*
import javax.inject.Inject

class NoteConfigurationListFragment : BaseFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: FragmentNoteConfigurationListViewBinding

    private val viewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory)
            .get(NoteConfigurationViewModel::class.java)
    }

    private val adapter by lazy { NoteConfigurationListAdapter(viewModel) }

    private val navController by lazy {findNavController()}

    init {
        Injector.get().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_note_configuration_list_view,container,false)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_note_configuration_list_view, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel = this@NoteConfigurationListFragment.viewModel
            lifecycleOwner = this@NoteConfigurationListFragment
            recyclerViewSelectedNotesList.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewSelectedNotesList.adapter = this@NoteConfigurationListFragment.adapter
            recyclerViewSelectedNotesList.addItemDecoration(
                DividerItemDecoration(
                    requireContext(), DividerItemDecoration.VERTICAL
                )
            )
        }
        super.onViewCreated(view, savedInstanceState)
        listConfig.setOnClickListener {
            navController.navigate(R.id.action_noteConfigurationListFragment_to_noteEditConfigFragment)
        }
//        floatingActionButton.setOnClickListener {
//            navController.navigate(R.id.action_noteConfigurationListFragment_to_noteEditConfigFragment)
//        }
    }
//
    override fun setUpObservers() {
//        viewModel.displayPacket.observeNotNull {
//            Snackbar.make(binding.root, "Clicked packet id: $it", Snackbar.LENGTH_LONG).show()
//        }
        viewModel.loadConfig.observeNotNull {
            navController.navigate(R.id.action_noteConfigurationListFragment_to_noteEditConfigFragment)
        }
    }
}