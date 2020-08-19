package com.example.roloandroid.contacts_list.list_types

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.roloandroid.R
import com.example.roloandroid.databinding.ContactsListAllFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsListAllFragment : Fragment() {

    companion object {
        fun newInstance() = ContactsListAllFragment()
    }

    val viewModel: ContactsListAllViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = ContactsListAllFragmentBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            vm = viewModel
            setLifecycleOwner {  this@ContactsListAllFragment.lifecycle}
        }
        return bind.root

    }


}