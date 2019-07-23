package com.trippntechnology.tnt.flashcards.ux.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.databinding.ListItemEnabledNotesBinding
import com.trippntechnology.tnt.flashcards.objects.noteconfiguration.NoteConfiguration
import com.trippntechnology.tnt.flashcards.ux.fragments.main.noteconfigurationlist.NoteConfigurationListViewModel

class NoteConfigurationListAdapter(
    private val viewModel: NoteConfigurationListViewModel
) : ListAdapter<NoteConfiguration, NoteConfigurationListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent).apply {
            binding.viewModel = viewModel
            binding.listItemRowTemp.setOnLongClickListener {
                //FIXME for some reason the attribute android:onLongClick doesn't exist. Therefore this is being set here
                viewModel.loadConfig(binding.noteConfiguration)
                return@setOnLongClickListener true
            }
        }
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.noteConfiguration = getItem(position)
    }

    class ViewHolder(
        parent: ViewGroup, val binding: ListItemEnabledNotesBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.list_item_enabled_notes, parent, false
        )
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {

        private val DIFF_CALLBACK: DiffUtil.ItemCallback<NoteConfiguration> =
            object : DiffUtil.ItemCallback<NoteConfiguration>() {
                override fun areContentsTheSame(oldItem: NoteConfiguration, newItem: NoteConfiguration): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }

                override fun areItemsTheSame(oldItem: NoteConfiguration, newItem: NoteConfiguration): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }
}