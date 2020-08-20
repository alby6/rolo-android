package com.example.roloandroid.contacts_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.roloandroid.R
import com.example.roloandroid.databinding.ContactsDetailFragmentBinding
import com.example.roloandroid.googler_wrappers.data
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ContactsDetailFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() =
            ContactsDetailFragment()
        const val UID_KEY = "UID_KEY"
    }

    val viewModel: ContactsDetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //sets rounded corners for BottomSheetDialogFragment
        setStyle(STYLE_NORMAL, R.style. AppBottomSheetDialogTheme);

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind =  ContactsDetailFragmentBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            vm = viewModel
            setLifecycleOwner {  this@ContactsDetailFragment.lifecycle}
        }


        //sets top offset for the bottom sheet (10% off from top of screen)
        (dialog as? BottomSheetDialog)?.let {
            it.behavior.peekHeight = (requireContext()
                .resources
                .displayMetrics
                .heightPixels * 0.9)
                .toInt()
        }

        //binds text and images to xml
        val detailUid = arguments?.getInt(UID_KEY)
        viewModel.loadUserDataObservable.observe(this, Observer {users ->
            run loop@{
                users.data?.forEach { user ->
                    if (user.uid == detailUid) {
                        bind.user = user
                        return@loop
                    }
                }
            }

        })

        return bind.root

    }



}