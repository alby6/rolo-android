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
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class ContactsListViewModel @ViewModelInject constructor(

    private val observeRemoteDataUseCase : ObserveRemoteDataUseCase,
    private val executeRemoteDataRequestUseCase: ExecuteRemoteDataRequestUseCase,
    private val loadUserDataUseCase: LoadUserDataUseCase,
    private val invertStarStatusUseCase : InvertStarStatusUseCase

): ViewModel(), StarInverterInterface {

    private val starredClickLiveData : MutableLiveData<Event<Unit>> = MutableLiveData()
    val starredClickObservable : LiveData<Event<Unit>> = starredClickLiveData

    private val allClickLiveData : MutableLiveData<Event<Unit>> = MutableLiveData()
    val allClickObservable : LiveData<Event<Unit>> = allClickLiveData

    private val dataUpdatedLiveData = observeRemoteDataUseCase(Unit).asLiveData()
    val contactsListRefreshRequiredObservable : LiveData<Result<List<User>>> = dataUpdatedLiveData.switchMap {
        loadUserDataUseCase(Unit).asLiveData()
    }

    private var currentButton = ButtonType.ALL

    //notifies buttons that they must invert color
    val invertButtonColorLiveData : MutableLiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(allClickLiveData) {
            postValue(true)
        }
        addSource(starredClickLiveData) {
            postValue(true)
        }
    }

    //changes favorite status
    override fun invertFavoriteStatus(uid : Int) {
        viewModelScope.launch {
            invertStarStatusUseCase(uid)
        }
    }

    fun getUserCache() : Flow<Result<List<User>>> {
        return loadUserDataUseCase(Unit)
    }

    //gets user data from server
    fun executeRemoteDataRequest() {
        viewModelScope.launch {
            executeRemoteDataRequestUseCase(Unit)
        }
    }

    //when you only want to see favorite contacts
    fun starredClick() {
        if (currentButton == ButtonType.STAR) return
        starredClickLiveData.value = Event(Unit)
        currentButton = ButtonType.STAR
    }

    //when you only want to see all contacts
    fun allClick() {
        if (currentButton == ButtonType.ALL) return
        allClickLiveData.value = Event(Unit)
        currentButton = ButtonType.ALL
    }


}

enum class ButtonType {
    STAR, ALL
}
