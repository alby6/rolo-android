package com.example.roloandroid.use_case

import com.example.roloandroid.di.IoDispatcher
import com.example.roloandroid.googler_wrappers.UseCase
import com.example.roloandroid.repo.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@ExperimentalCoroutinesApi
class InvertStarStatusUseCase @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository
) : UseCase<Int, Unit>(ioDispatcher) {
    override suspend fun execute(parameters: Int) {
        userRepository.invertStarStatus(parameters)
    }

}