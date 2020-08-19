package com.example.roloandroid.contacts_list.list_types

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.roloandroid.data.User
import com.example.roloandroid.googler_wrappers.Result
import com.example.roloandroid.use_case.ExecuteRemoteDataRequestUseCase
import com.example.roloandroid.use_case.LoadUserDataUseCase
import com.example.roloandroid.use_case.ObserveRemoteDataUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.switchMap
import kotlinx.coroutines.launch

class ContactsListAllViewModel @ViewModelInject constructor(
     private val observeRemoteDataUseCase : ObserveRemoteDataUseCase,
     private val executeRemoteDataRequestUseCase: ExecuteRemoteDataRequestUseCase,
     private val loadUserDataUseCase: LoadUserDataUseCase
) : ViewModel() {


    private val dataUpdatedLiveData = observeRemoteDataUseCase(Unit).asLiveData()

    val contactsListRefreshRequiredObservable : LiveData<Unit> = dataUpdatedLiveData.switchMap {
        liveData {
            emit(Unit)
        }
    }
    val wtf : LiveData<Result<List<User>>> = dataUpdatedLiveData.switchMap {
        println("WTFFFF")
        loadUserDataUseCase(Unit).asLiveData()
    }


    fun executeRemoteDataRequest() {
        viewModelScope.launch {
            executeRemoteDataRequestUseCase(Unit)
        }
    }




}