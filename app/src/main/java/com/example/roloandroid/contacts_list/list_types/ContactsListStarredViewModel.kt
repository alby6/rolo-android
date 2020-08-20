package com.example.roloandroid.contacts_list.list_types

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.roloandroid.data.User
import com.example.roloandroid.googler_wrappers.Result
import com.example.roloandroid.use_case.FilterStarsUseCase
import com.example.roloandroid.use_case.LoadUserDataUseCase
import com.example.roloandroid.use_case.ObserveRemoteDataUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ContactsListStarredViewModel @ViewModelInject constructor(
    private val observeRemoteDataUseCase : ObserveRemoteDataUseCase,
    private val loadUserDataUseCase: LoadUserDataUseCase,
    private val filterStarsUseCase: FilterStarsUseCase
) : ViewModel() {

    private val dataUpdatedLiveData = observeRemoteDataUseCase(Unit).asLiveData()
    val contactsListRefreshRequiredObservable : LiveData<Result<List<User>>> = dataUpdatedLiveData.switchMap {
        loadUserDataUseCase(Unit).asLiveData()
    }


}