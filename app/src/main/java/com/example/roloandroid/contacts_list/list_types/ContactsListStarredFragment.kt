package com.example.roloandroid.contacts_list.list_types

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roloandroid.R
import com.example.roloandroid.contacts_list.ContactsListAdapter
import com.example.roloandroid.contacts_list.NavigationInterface
import com.example.roloandroid.databinding.ContactsListStarredFragmentBinding
import com.example.roloandroid.googler_wrappers.data
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ContactsListStarredFragment : Fragment(), NavigationInterface {

    companion object {
        fun newInstance() = ContactsListStarredFragment()
    }

    val viewModel: ContactsListStarredViewModel by viewModels()
    lateinit var adapter : ContactsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = ContactsListStarredFragmentBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            vm = viewModel
            setLifecycleOwner {  this@ContactsListStarredFragment.lifecycle}
        }
        setObservables()
        adapter = ContactsListAdapter(this@ContactsListStarredFragment)
        bind.recyclerView.adapter = adapter
        val llm = LinearLayoutManager(requireContext())
        bind.recyclerView.layoutManager = llm

        return bind.root
    }

    fun setObservables() {
        viewModel.contactsListRefreshRequiredObservable.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.data!!)
        })
    }

    override fun navigate(bundle: Bundle) {
        findNavController().navigate(R.id.action_contactsListFragment_to_contactsDetailFragment, bundle)
    }


}