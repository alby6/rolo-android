package com.example.roloandroid.contacts_detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.roloandroid.data.User
import com.example.roloandroid.googler_wrappers.Result
import com.example.roloandroid.googler_wrappers.data
import com.example.roloandroid.use_case.LoadUserDataUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
class ContactsDetailViewModel @ViewModelInject constructor(
    private val loadUserDataUseCase: LoadUserDataUseCase
): ViewModel() {

    private val loadUserDataLiveData = loadUserDataUseCase(Unit).asLiveData()
    val loadUserDataObservable : LiveData<Result<List<User>>> = loadUserDataLiveData

}