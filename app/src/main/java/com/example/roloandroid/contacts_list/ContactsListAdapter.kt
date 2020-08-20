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
        //return false
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        //return false
        return (
                oldItem.uid == newItem.uid &&
                oldItem.isFavorite == newItem.isFavorite
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

    override fun onBindViewHolder(holder: ContactsCellViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


@ExperimentalCoroutinesApi
class ContactsCellViewHolder(
    private val vdb : ViewDataBinding,
    private val navInterface : NavigationInterface,
    private val starInverterInterface: StarInverterInterface
) : RecyclerView.ViewHolder(vdb.root) {
    fun bind(user : User) {
        user.apply {
            (vdb as? CellLayoutBinding)?.let {bind ->
                bind.barLayout.setOnClickListener {
                    val bundle = bundleOf(ContactsDetailFragment.UID_KEY to user.uid)
                    navInterface.navigate(bundle)
                }
                val starIV = bind.star
                starIV.setOnClickListener {

                    if (starIV.getTag(R.string.iv_tag) as Boolean) {
                        println("WTF")
                        starIV.setTag(R.string.iv_tag, false)
                        starIV.setBackgroundResource(R.drawable.ic_gray_circle)
                    } else {
                        println("WTFB")
                        starIV.setTag(R.string.iv_tag, true)
                        starIV.setBackgroundResource(R.drawable.ic_orange_circle)
                    }
                    starInverterInterface.invertStarStatus(user.uid-1) //uid starts at 1 but index starts at 0,
                }
                bind.user = user
                bind.executePendingBindings()
            }
        }
    }
}

