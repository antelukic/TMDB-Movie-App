package com.lukic.domain.usecase

interface QueryUseCase<R> {
    suspend operator fun invoke(): R
}

interface QueryUseCaseWithParam<T1, R> {
    suspend operator fun invoke(param: T1): R
}
