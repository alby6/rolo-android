package com.example.roloandroid.contacts_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roloandroid.googler_wrappers.Event

class ContactsListViewModel @ViewModelInject constructor(): ViewModel() {
    // TODO: Implement the ViewModel

    private val starredClick : MutableLiveData<Event<Unit>> = MutableLiveData()
    val starredClickObservable : LiveData<Event<Unit>> = starredClick

    private val allClick : MutableLiveData<Event<Unit>> = MutableLiveData()
    val allClickObservable : LiveData<Event<Unit>> = allClick


    fun starredClick() {
        starredClick.value = Event(Unit)
    }

    fun allClick() {
        allClick.value = Event(Unit)
    }

}