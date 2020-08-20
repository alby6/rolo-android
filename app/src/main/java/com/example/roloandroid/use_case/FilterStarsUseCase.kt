package com.example.roloandroid.use_case

import com.example.roloandroid.data.User
import com.example.roloandroid.di.IoDispatcher
import com.example.roloandroid.googler_wrappers.UseCase
import kotlinx.coroutines.CoroutineDispatcher


class FilterStarsUseCase(
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : UseCase<List<User>, List<User>>(ioDispatcher) {
    override suspend fun execute(parameters: List<User>): List<User> {
        val filteredList = mutableListOf<User>()
        parameters.forEach {user ->
            if (user.isFavorite) {
                filteredList.add(user)
            }
        }
        return filteredList
    }

}