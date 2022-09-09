package com.lukic.domain.usecase

import kotlinx.coroutines.flow.Flow

interface QueryUseCase<R> {
    operator fun invoke(): Flow<R>
}
