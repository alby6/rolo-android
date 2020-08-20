package com.example.roloandroid.contacts_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roloandroid.contacts_detail.ContactsDetailFragment
import com.example.roloandroid.data.User
import com.example.roloandroid.databinding.CellLayoutBinding


class ContactsListAdapter(private val navInterface : NavigationInterface) : ListAdapter<User, GenericViewHolder>(object : DiffUtil.ItemCallback<User>(){
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
        return GenericViewHolder(bind, navInterface)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


class GenericViewHolder(
    private val vdb : ViewDataBinding,
    private val navInterface : NavigationInterface
) : RecyclerView.ViewHolder(vdb.root) {
    fun bind(user : User) {
        user.apply {
            (vdb as? CellLayoutBinding)?.let {bind ->
                bind.barLayout.setOnClickListener {
                    val bundle = bundleOf(ContactsDetailFragment.UID_KEY to user.uid)
                    navInterface.navigate(bundle)
                }
                bind.star.setOnClickListener {

                }
                bind.user = user
                bind.executePendingBindings()
            }
        }
    }
}

