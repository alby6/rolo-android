package com.example.roloandroid.use_case

import com.example.roloandroid.data.User
import com.example.roloandroid.di.IoDispatcher
import com.example.roloandroid.googler_wrappers.FlowUseCase
import com.example.roloandroid.googler_wrappers.Result
import com.example.roloandroid.googler_wrappers.UseCase
import com.example.roloandroid.repo.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


class LoadUserDataUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository
) : FlowUseCase<Unit, List<User>>(dispatcher) {
    override fun execute(parameters: Unit): Flow<Result<List<User>>> {
        return userRepository.getUserCache()
    }

}