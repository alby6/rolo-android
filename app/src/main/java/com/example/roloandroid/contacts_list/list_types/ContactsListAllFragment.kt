package com.example.roloandroid.contacts_list.list_types

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.roloandroid.R
import com.example.roloandroid.databinding.ContactsListAllFragmentBinding
import com.example.roloandroid.googler_wrappers.data
import com.example.roloandroid.repo.UserRepository
import com.example.roloandroid.use_case.ExecuteRemoteDataRequestUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
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
            println("WTF33")
        })
        viewModel.wtf.observe(viewLifecycleOwner, Observer {
            it.data?.forEach {
                println(it)
                println(it.profilePicture)
            }
        })
        viewModel.executeRemoteDataRequest()
    }



}