package com.ozzy.githubsearcher.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ozzy.githubsearcher.api.model.User
import com.ozzy.githubsearcher.databinding.ItemUserBinding

/**
 * Created by Oğuzhan Karacan on 2.01.2021.
 */
class UsersAdapter(val onClick: (User) -> Unit) :
    ListAdapter<User, UsersAdapter.UserViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.user = user
            binding.root.setOnClickListener { onClick.invoke(user) }
            binding.executePendingBindings()
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(
                oldUser: User,
                newUser: User,
            ): Boolean =
                oldUser.id == newUser.id

            override fun areContentsTheSame(
                oldUser: User,
                newUser: User,
            ): Boolean =
                oldUser == newUser
        }
    }
}