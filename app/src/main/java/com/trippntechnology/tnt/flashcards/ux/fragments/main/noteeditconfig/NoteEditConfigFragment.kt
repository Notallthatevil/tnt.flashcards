package com.trippntechnology.tnt.flashcards.ux.fragments.main.noteeditconfig

//import com.trippntechnology.tnt.flashcards.databinding.FragmentEditConfigBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.databinding.FragmentEditConfigBinding
import com.trippntechnology.tnt.flashcards.injector.Injector
import com.trippntechnology.tnt.flashcards.util.fragments.BaseFragment
import com.trippntechnology.tnt.flashcards.ux.fragments.main.noteeditconfig.NoteEditConfigViewModel.Companion.EVENT_FINISHED
import com.trippntechnology.tnt.flashcards.ux.fragments.main.noteeditconfig.NoteEditConfigViewModel.Companion.EVENT_SAVE
import com.vikingsen.inject.viewmodel.ViewModelFactory
import javax.inject.Inject

class NoteEditConfigFragment : BaseFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory)
            .get(NoteEditConfigViewModel::class.java)
    }
    private lateinit var binding: FragmentEditConfigBinding

    private val args by navArgs<NoteEditConfigFragmentArgs>()

    init {
        Injector.get().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //            return inflater.inflate(R.layout.fragment_edit_config,container,false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_config, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel = this@NoteEditConfigFragment.viewModel
            lifecycleOwner = this@NoteEditConfigFragment
        }
        viewModel.setConfigId(args.configId)
    }

    override fun setUpObservers() {
        viewModel.viewModelEvent.observeNotNull {
            when (it) {
                EVENT_SAVE -> {
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
                            viewModel.saveNoteConfig(args.configId, name, noteList)
                        }
                    }
                }
                EVENT_FINISHED -> {
                    findNavController().popBackStack()
                }
            }

        }
    }
}