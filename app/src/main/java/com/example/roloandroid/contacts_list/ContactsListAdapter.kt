package com.example.roloandroid.contacts_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roloandroid.data.User
import com.example.roloandroid.databinding.CellLayoutBinding


class ContactsListAdapter(navInterface : NavigationInterface) : ListAdapter<User, GenericViewHolder>(object : DiffUtil.ItemCallback<User>(){
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return (oldItem == newItem)
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return (oldItem.uid == newItem.uid)
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {

        val bind = CellLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
        false
        )
        return GenericViewHolder(bind)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


class GenericViewHolder(val vdb : ViewDataBinding) : RecyclerView.ViewHolder(vdb.root) {
    fun bind(user : User) {
        user.apply {
            (vdb as? CellLayoutBinding)?.let {bind ->
                bind.user = user
                bind.executePendingBindings()
            }
        }
    }
}

