package com.lukic.domain.usecase

interface CommandUseCaseWithParam<T1> {
    suspend operator fun invoke(param: T1)
}
