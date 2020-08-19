package com.example.roloandroid.use_case

import com.example.roloandroid.di.IoDispatcher
import com.example.roloandroid.googler_wrappers.FlowUseCase
import com.example.roloandroid.googler_wrappers.Result
import com.example.roloandroid.repo.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class ObserveRemoteDataUseCase @Inject constructor(
    private val userRepository: UserRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, Long>(ioDispatcher) {
    override fun execute(parameters: Unit): Flow<Result<Long>> =
        userRepository.getRemoteDataObservable().map {
            Result.Success(it)
        }
}

