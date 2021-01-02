package com.ozzy.githubsearcher.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ozzy.githubsearcher.api.model.Repository
import com.ozzy.githubsearcher.databinding.ItemRepositoryBinding

/**
 * Created by Oğuzhan Karacan on 1.01.2021.
 */
class RepositoriesAdapter(val onClick: (Repository) -> Unit) :
    ListAdapter<Repository, RepositoriesAdapter.RepositoryViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepositoryBinding.inflate(inflater, parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RepositoryViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Repository) {
            binding.repository = repo
            binding.root.setOnClickListener { onClick.invoke(repo) }
            binding.executePendingBindings()
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(
                oldRepository: Repository,
                newRepository: Repository
            ): Boolean =
                oldRepository.id == newRepository.id

            override fun areContentsTheSame(
                oldRepository: Repository,
                newRepository: Repository
            ): Boolean =
                oldRepository == newRepository
        }
    }
}