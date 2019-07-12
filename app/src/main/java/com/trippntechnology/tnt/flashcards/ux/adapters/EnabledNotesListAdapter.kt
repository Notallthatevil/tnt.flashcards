package com.trippntechnology.tnt.flashcards.ux.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.databinding.ListItemEnabledNotesBinding
import com.trippntechnology.tnt.flashcards.objects.enablednotes.EnabledNotes
import com.trippntechnology.tnt.flashcards.ux.activities.main.MainViewModel

class EnabledNotesListAdapter(
    private val viewModel: MainViewModel
) : ListAdapter<EnabledNotes, EnabledNotesListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent).apply {
            binding.viewModel = viewModel
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.enabledNotesConfig = getItem(position)
    }

    class ViewHolder(
        parent: ViewGroup, val binding: ListItemEnabledNotesBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_enabled_notes,
                parent,
                false
            )
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {

        private val DIFF_CALLBACK: DiffUtil.ItemCallback<EnabledNotes> =
            object : DiffUtil.ItemCallback<EnabledNotes>() {
                override fun areContentsTheSame(
                    oldItem: EnabledNotes,
                    newItem: EnabledNotes
                ): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }

                override fun areItemsTheSame(
                    oldItem: EnabledNotes,
                    newItem: EnabledNotes
                ): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }
}