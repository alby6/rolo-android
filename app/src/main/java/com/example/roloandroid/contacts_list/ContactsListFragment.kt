package com.example.roloandroid.contacts_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.roloandroid.contacts_list.list_types.ContactsListAllFragment
import com.example.roloandroid.contacts_list.list_types.ContactsListStarredFragment
import com.example.roloandroid.databinding.ContactsListFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsListFragment : Fragment() {

    companion object {
        fun newInstance() =
            ContactsListFragment()

        const val NUM_PAGES = 2
    }

    val viewModel: ContactsListViewModel by viewModels()
    lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = ContactsListFragmentBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            vm = viewModel
            setLifecycleOwner { this@ContactsListFragment.lifecycle }
        }

        viewPager = bind.viewPager
        viewPager.adapter = ScreenSlidePagerAdapter(this@ContactsListFragment)
        return bind.root
    }




    private inner class ScreenSlidePagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {

        override fun getItemCount(): Int = NUM_PAGES
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    ContactsListAllFragment()
                }
                else -> {
                    ContactsListStarredFragment()
                }
            }

        }
    }
}



