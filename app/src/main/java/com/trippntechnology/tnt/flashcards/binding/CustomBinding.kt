package com.trippntechnology.tnt.flashcards.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

object CustomBinding {

    @JvmStatic
    @BindingAdapter("list")
    fun <T, VH : RecyclerView.ViewHolder> setItems(recyclerView: RecyclerView, list: List<T>?) {
        list ?: return

        @Suppress("UNCHECKED_CAST") when {
            recyclerView.adapter is ListAdapter<*, *> -> {
                val adapter = recyclerView.adapter as ListAdapter<T, VH>
                adapter.submitList(list)
            }
            else -> error("Must use a ListAdapter for app:list")
        }
    }

}