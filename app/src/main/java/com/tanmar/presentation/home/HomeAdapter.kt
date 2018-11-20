package com.tanmar.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tanmar.R
import com.tanmar.data.github.RepoEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home_repository.*

class HomeAdapter(private val listener: Listener) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val items: MutableList<RepoEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_repository, parent, false),
            listener
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(newItems: List<RepoEntity>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(override val containerView: View, private val listener: Listener) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: RepoEntity) {
            textViewHomeRepositoryName.text = item.name
            containerView.setOnClickListener {
                listener.onSelectedRepository(item)
            }
        }
    }

    interface Listener {
        fun onSelectedRepository(repository: RepoEntity)
    }
}