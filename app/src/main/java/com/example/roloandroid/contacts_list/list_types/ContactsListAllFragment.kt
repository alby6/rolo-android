package com.example.roloandroid.contacts_list.list_types

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.roloandroid.R
import com.example.roloandroid.databinding.ContactsListAllFragmentBinding
import com.example.roloandroid.repo.UserRepository
import com.example.roloandroid.use_case.ExecuteRemoteDataRequestUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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
        setObservables()
        return bind.root

    }



    fun setObservables() {
        viewModel.contactsListRefreshRequiredObservable.observe(viewLifecycleOwner, Observer {
            println("This works")
        })

        viewModel.executeRemoteDataRequest()
    }



}