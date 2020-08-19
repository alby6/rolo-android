package com.example.roloandroid.contacts_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.roloandroid.R
import com.example.roloandroid.contacts_detail.ContactsDetailFragment
import com.example.roloandroid.databinding.ContactsListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactsListFragment : Fragment(), NavigationInterface {

    companion object {
        fun newInstance() =
            ContactsListFragment()
    }

    val viewModel: ContactsListViewModel by viewModels()

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
            setLifecycleOwner {  this@ContactsListFragment.lifecycle}
        }
        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun navigate() {
        findNavController().navigate(R.id.action_contactsListFragment_to_contactsDetailFragment)
    }

}