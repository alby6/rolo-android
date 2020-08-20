package com.example.roloandroid.contacts_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.roloandroid.data.User
import com.example.roloandroid.googler_wrappers.Event
import com.example.roloandroid.googler_wrappers.Result
import com.example.roloandroid.use_case.ExecuteRemoteDataRequestUseCase
import com.example.roloandroid.use_case.InvertStarStatusUseCase
import com.example.roloandroid.use_case.LoadUserDataUseCase
import com.example.roloandroid.use_case.ObserveRemoteDataUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class ContactsListViewModel @ViewModelInject constructor(

    private val observeRemoteDataUseCase : ObserveRemoteDataUseCase,
    private val executeRemoteDataRequestUseCase: ExecuteRemoteDataRequestUseCase,
    private val loadUserDataUseCase: LoadUserDataUseCase,
    private val invertStarStatusUseCase : InvertStarStatusUseCase

): ViewModel(), StarInverterInterface {

    private val starredClick : MutableLiveData<Event<Unit>> = MutableLiveData()
    val starredClickObservable : LiveData<Event<Unit>> = starredClick

    private val allClick : MutableLiveData<Event<Unit>> = MutableLiveData()
    val allClickObservable : LiveData<Event<Unit>> = allClick

    private val dataUpdatedLiveData = observeRemoteDataUseCase(Unit).asLiveData()
    val contactsListRefreshRequiredObservable : LiveData<Result<List<User>>> = dataUpdatedLiveData.switchMap {
        loadUserDataUseCase(Unit).asLiveData()
    }

    override fun invertStarStatus(uid : Int) {
        viewModelScope.launch {
            invertStarStatusUseCase(uid)
        }
    }

    fun getUserCache() : Flow<Result<List<User>>> {
        return loadUserDataUseCase(Unit)
    }



    fun executeRemoteDataRequest() {
        viewModelScope.launch {
            executeRemoteDataRequestUseCase(Unit)
        }
    }
    fun starredClick() {
        starredClick.value = Event(Unit)
    }

    fun allClick() {
        allClick.value = Event(Unit)
    }


}