package com.example.innovationmvvmtest.resource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResponseType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResponseType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchedResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResponseType) -> Boolean = { true }
) = flow {
    val data = query().first()
    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))
        try {
            saveFetchedResult(fetch())
            query().map {
                Resource.Success(it)
            }
        }
        catch (throwable: Throwable) {
            query().map {
                Resource.Error(throwable, it)
            }
        }
    }
    else {
        query().map {
            Resource.Success(it)
        }
    }

    emitAll(flow)
}
