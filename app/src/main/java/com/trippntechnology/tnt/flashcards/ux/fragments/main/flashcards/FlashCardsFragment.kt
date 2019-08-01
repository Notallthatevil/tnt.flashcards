package com.trippntechnology.tnt.flashcards.ux.fragments.main.flashcards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.trippntechnology.tnt.androidbaseutils.ux.fragments.BaseFragment
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.databinding.FragmentFlashCardsBinding
import com.trippntechnology.tnt.flashcards.injector.Injector
import com.vikingsen.inject.viewmodel.ViewModelFactory
import javax.inject.Inject

class FlashCardsFragment : BaseFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(FlashCardsViewModel::class.java)
    }
    private lateinit var binding: FragmentFlashCardsBinding

    private val args by navArgs<FlashCardsFragmentArgs>()

    init {
        Injector.get().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flash_cards, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel = this@FlashCardsFragment.viewModel
            lifecycleOwner = this@FlashCardsFragment
        }
       viewModel.loadPacket(args.configId)
    }


    override fun setUpObservers() {
        viewModel.submitAnswer.observe {
            it ?: return@observe
            if (binding.flashCard.submitAnswer(it)) {
                Snackbar.make(binding.root, "Correct", Snackbar.LENGTH_LONG).show()
            }else{
                Snackbar.make(binding.root, "Incorrect", Snackbar.LENGTH_LONG).show()
            }
        }
        viewModel.loadedConfig.observe{
            it?:return@observe
            binding.flashCard.initializeCardPacket(it.getnotes())
        }
        binding.flashCard.finishedList.observe{
            findNavController().popBackStack()
        }


    }

}