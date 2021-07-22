package com.s24.mastodonclient.repository

import com.s24.mastodonclient.MastodonApi
import com.s24.mastodonclient.entity.Toot
import com.s24.mastodonclient.entity.UserCredential
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TootRepository (
    private val userCredential: UserCredential
) {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(userCredential.instanceUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val api = retrofit.create(MastodonApi::class.java)

    suspend fun fetchPublicTimeline(
        maxId: String? = null,
        onlyMedia: Boolean = false
    ) = withContext(Dispatchers.IO){
        api.fetchPublicTimeline(
            maxId = maxId,
            onlyMedia = onlyMedia
        )
    }
    suspend fun fetchHomeTimeLine(
        maxId: String? =null,
    ) = withContext(Dispatchers.IO){
        api.fetchHomeTimeline(
            accessToken = "Bearer ${userCredential.accessToken}",
            maxId = maxId
        )
    }
    suspend fun postToot(
        status: String
    ): Toot = withContext(Dispatchers.IO){
        return@withContext api.postToot(
            "Bearer ${userCredential.accessToken}",
            status
        )
    }

    suspend fun delete(id: String) = withContext(Dispatchers.IO) {
        api.deleteToot(
            "Bearer ${userCredential.accessToken}",
            id
        )
    }
}