package com.lukic.domain.usecase

import kotlinx.coroutines.flow.Flow

interface QueryUseCase<R> {
    operator fun invoke(): Flow<R>
}

interface QueryUseCaseWithParam<T1, R> {
    operator fun invoke(param: T1): Flow<R>
}
