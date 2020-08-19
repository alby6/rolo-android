package com.example.roloandroid.contacts_list.list_types

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.roloandroid.R
import com.example.roloandroid.databinding.ContactsListStarredFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsListStarredFragment : Fragment() {

    companion object {
        fun newInstance() = ContactsListStarredFragment()
    }

    val viewModel: ContactsListStarredViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ContactsListStarredFragmentBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            vm = viewModel
            setLifecycleOwner {  this@ContactsListStarredFragment.lifecycle}
        }
        return binding.root
    }


}