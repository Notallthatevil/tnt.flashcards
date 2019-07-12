package com.trippntechnology.tnt.flashcards.ux.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.databinding.FragmentFlashCardBinding
import com.trippntechnology.tnt.flashcards.injector.Injector
import com.trippntechnology.tnt.flashcards.util.fragments.BaseFragment
import com.trippntechnology.tnt.flashcards.ux.activities.main.MainViewModel
import com.vikingsen.inject.viewmodel.ViewModelFactory
import javax.inject.Inject

class FlashCardFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(requireActivity(), viewModelFactory).get(
        MainViewModel::class.java) }
    private lateinit var binding: FragmentFlashCardBinding

    init {
        Injector.get().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flash_card, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel = this@FlashCardFragment.viewModel
            lifecycleOwner = this@FlashCardFragment
        }
        super.onViewCreated(view,savedInstanceState)
    }

    override fun setUpObservers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}