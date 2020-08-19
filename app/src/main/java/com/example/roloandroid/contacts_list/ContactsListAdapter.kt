package com.example.roloandroid.contacts_list

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roloandroid.data.User


class ContactsListAdapter(navInterface : NavigationInterface) : ListAdapter<User, GenericViewHolder>(object : DiffUtil.ItemCallback<User>(){
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        TODO("Not yet implemented")
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {

        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}


class GenericViewHolder(vdb : ViewDataBinding) : RecyclerView.ViewHolder(vdb.root) {



}

