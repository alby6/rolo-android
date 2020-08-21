package com.example.roloandroid.contacts_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.roloandroid.R
import com.example.roloandroid.data.User
import com.example.roloandroid.databinding.ContactsListFragmentBinding
import com.example.roloandroid.googler_wrappers.EventObserver
import com.example.roloandroid.googler_wrappers.data
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ContactsListFragment : Fragment(), NavigationInterface {

    companion object {
        fun newInstance() =
            ContactsListFragment()
    }

    val viewModel: ContactsListViewModel by viewModels()
    private lateinit var adapter : ContactsListAdapter
    private var adapterMutex = Mutex()
    private var viewStarredContactsOnly = false

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
        adapter = ContactsListAdapter(
            this@ContactsListFragment,
            viewModel
        )

        val llm = LinearLayoutManager(requireContext())
        bind.recyclerView.adapter = adapter
        bind.recyclerView.layoutManager = llm
    }


    private fun setClickObservables() {

        //when all-contacts button is clicked, this will repopulate the recycler view
        viewModel.allClickObservable.observe(viewLifecycleOwner, EventObserver {
            lifecycleScope.launch {
                adapterMutex.withLock {
                    viewStarredContactsOnly = false
                    viewModel.getUserCache().collect {result ->
                        result.data?.let {data ->
                            adapter.submitList(data)
                        }
                    }
                }
            }
        })

        //when favorite-contacts button is clicked, this will repopulate the recycler view
        viewModel.starredClickObservable.observe(viewLifecycleOwner, EventObserver {
            lifecycleScope.launch {
                adapterMutex.withLock {
                    viewStarredContactsOnly = true
                    viewModel.getUserCache().collect {result ->
                        result.data?.let {data ->
                            adapter.submitList(filterByFavorites(data))
                        }
                    }
                }
            }
        })
    }

    //when network request is finished, this observable will be notified and the observer will activate
    private fun setAdapterObservable() {
        viewModel.contactsListRefreshRequiredObservable.observe(viewLifecycleOwner, Observer { result ->
            lifecycleScope.launch {
                adapterMutex.withLock {
                    var list = result.data!!
                    if (viewStarredContactsOnly) {
                        list = filterByFavorites(list)
                    }
                    adapter.submitList(list)
                }
            }

        })
    }

    private fun filterByFavorites(list : List<User>) : List<User> {
        return list.filter {
            it.isFavorite
        }
    }


    override fun navigate(bundle: Bundle) {
        findNavController().navigate(R.id.action_contactsListFragment_to_contactsDetailFragment, bundle)
    }


}



