package com.example.roloandroid.use_case

import com.example.roloandroid.di.IoDispatcher
import com.example.roloandroid.googler_wrappers.UseCase
import com.example.roloandroid.repo.UserRepository
import kotlinx.coroutines.CoroutineDispatcher


class ExecuteRemoteDataRequestUseCase(
    val userRepository: UserRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : UseCase<Unit, Unit>(ioDispatcher) {
    override suspend fun execute(parameters: Unit) {
        userRepository.executeRemoteDataRequest()
    }

}