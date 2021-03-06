package com.example.roloandroid.contacts_list

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.roloandroid.R
import com.example.roloandroid.contacts_detail.ContactsDetailFragment
import com.example.roloandroid.data.User
import com.example.roloandroid.databinding.CellLayoutBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ContactsCellViewHolder(
    private val vdb : ViewDataBinding,
    private val navInterface : NavigationInterface,
    private val starInverterInterface: StarInverterInterface
) : RecyclerView.ViewHolder(vdb.root) {
    fun bind(user : User) {
        user.apply {
            (vdb as? CellLayoutBinding)?.let { bind ->
                bind.barLayout.setOnClickListener {
                    val bundle = bundleOf(ContactsDetailFragment.UID_KEY to user.uid)
                    navInterface.navigate(bundle)
                }

                val starIV = bind.star
                val miniStarIV = bind.miniStar

                starIV.setOnClickListener {
                    /*
                        this simply changes the icon for the button when clicked
                     */
                    if (starIV.getTag(R.string.iv_tag) as Boolean) {
                        starIV.setTag(R.string.iv_tag, false)
                        starIV.setBackgroundResource(R.drawable.ic_orange_circle)
                        miniStarIV.visibility = GONE
                    } else {
                        starIV.setTag(R.string.iv_tag, true)
                        starIV.setBackgroundResource(R.drawable.ic_gray_circle)
                        miniStarIV.visibility = VISIBLE
                    }
                    /*
                        This changes the status of the database/cache and requests an UI update for the recycler view.
                        uid starts at 1 but index starts at 0, so a -1 is required to balance it.
                     */
                    starInverterInterface.invertFavoriteStatus(user.uid-1)
                }
                bind.user = user
                bind.executePendingBindings()
            }
        }
    }

}
