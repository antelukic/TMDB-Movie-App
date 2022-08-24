package com.lukic.movieapp.domain.usecase

interface QueryUseCase<R> {
    operator fun invoke(): R
}

interface QueryUseCaseWithParam<T1, R> {
    operator fun invoke(param: T1): R
}
