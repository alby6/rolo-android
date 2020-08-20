package com.example.roloandroid.contacts_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.roloandroid.FadePageTransformer
import com.example.roloandroid.R
import com.example.roloandroid.databinding.ContactsListFragmentBinding
import com.example.roloandroid.googler_wrappers.EventObserver
import com.example.roloandroid.googler_wrappers.data
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ContactsListFragment : Fragment(), NavigationInterface {

    companion object {
        fun newInstance() =
            ContactsListFragment()
    }

    val viewModel: ContactsListViewModel by viewModels()
    lateinit var adapter : ContactsListAdapter

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

        setAdapter(bind)
        setClickObservables()
        setAdapterObservable()

        viewModel.executeRemoteDataRequest()
        return bind.root
    }

    private fun setAdapter(bind : ContactsListFragmentBinding) {
        adapter = ContactsListAdapter(this@ContactsListFragment)
        val llm = LinearLayoutManager(requireContext())
        bind.recyclerView.adapter = adapter
        bind.recyclerView.layoutManager = llm
    }


    private fun setClickObservables() {

        viewModel.allClickObservable.observe(viewLifecycleOwner, EventObserver {

        })
        viewModel.starredClickObservable.observe(viewLifecycleOwner, EventObserver {
        })
    }

    private fun setAdapterObservable() {
        viewModel.contactsListRefreshRequiredObservable.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.data!!)
            it.data?.forEach {user ->
                println(user)
                println(user.profilePicture)
            }
        })
    }
    override fun navigate(bundle: Bundle) {
        findNavController().navigate(R.id.action_contactsListFragment_to_contactsDetailFragment, bundle)
    }


}



