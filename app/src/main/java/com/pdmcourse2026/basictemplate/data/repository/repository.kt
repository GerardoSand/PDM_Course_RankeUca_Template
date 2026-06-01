package com.pdmcourse2026.basictemplate.data.repository

import com.pdmcourse2026.basictemplate.data.api.KtorClient
import com.pdmcourse2026.basictemplate.model.options
import io.ktor.client.call.body
import io.ktor.client.request.get

interface RankeUcaApi {
    suspend fun getOptions(): Result<List<options>>
    suspend fun vote(optionId: Int): Result<String>
}

class RankeUcaApiImpl : RankeUcaApi {
    override suspend fun getOptions(): Result<List<options>> {
        return try {
            val response: List<options> = KtorClient.client.get("/options").body()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun vote(optionId: Int): Result<String> {
        return try {
            val response: String = KtorClient.client.get("restaurants/$optionId").body()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}


