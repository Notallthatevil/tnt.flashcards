package com.trippntechnology.tnt.flashcards.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trippntechnology.tnt.flashcards.objects.noteconfiguration.NoteConfiguration
import com.trippntechnology.tnt.flashcards.views.SelectionView
import timber.log.Timber

object CustomBinding {

    @JvmStatic
    @BindingAdapter("list")
    fun <T, VH : RecyclerView.ViewHolder> setItems(recyclerView: RecyclerView, list: List<T>?) {
        list ?: return

        @Suppress("UNCHECKED_CAST") when {
            recyclerView.adapter is ListAdapter<*, *> -> {
                val adapter = recyclerView.adapter as ListAdapter<T, VH>
                adapter.submitList(list.sortedBy {
                    it.toString()
                })
            }
            else -> error("Must use a ListAdapter for app:list")
        }
    }

    @JvmStatic
    @BindingAdapter("setConfig")
    fun setConfig(selectionView: SelectionView,noteConfig:NoteConfiguration?){
        Timber.d("Note config = $noteConfig")
        noteConfig ?:return
        selectionView.loadConfig(noteConfig)
    }

}