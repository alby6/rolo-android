package com.example.roloandroid.contacts_list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roloandroid.R
import com.example.roloandroid.contacts_detail.ContactsDetailFragment
import com.example.roloandroid.data.User
import com.example.roloandroid.databinding.CellLayoutBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ContactsListAdapter(
    private val navInterface : NavigationInterface,
    private val starInverterInterface: StarInverterInterface
) : ListAdapter<User, ContactsCellViewHolder>(object : DiffUtil.ItemCallback<User>(){

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return (oldItem == newItem)
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return (
                return oldItem.uid == newItem.uid
                //oldItem.isFavorite == newItem.isFavorite
            )

    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsCellViewHolder {

        val bind = CellLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
        false
        )

        return ContactsCellViewHolder(
            bind,
            navInterface,
            starInverterInterface
        )
    }

    override fun submitList(list: List<User>?) {
        super.submitList(list)
    }

    override fun onBindViewHolder(holder: ContactsCellViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}



