package com.s24.mastodonclient

import com.s24.mastodonclient.entity.Account
import com.s24.mastodonclient.entity.Toot
import retrofit2.http.*

interface MastodonApi {

    @GET("api/v1/timelines/public")
    suspend fun fetchPublicTimeline(
        @Query("max_id") maxId: String? = null,
        @Query("only_media") onlyMedia: Boolean = false
    ): List<Toot>

    @GET("api/v1/timelines/home")
    suspend fun fetchHomeTimeline(
        @Header("Authorization") accessToken: String,
        @Query("max_id") maxId: String? = null
    ):List<Toot>

    @GET("api/v1/accounts/verify_credentials")
    suspend fun verifyAccountCredential(
        @Header("Authorization") accessToken: String
    ): Account

    @FormUrlEncoded
    @POST("api/v1/statuses")
    suspend fun postToot(
        @Header("Authorization") accessToken: String,
        @Field("status") status: String
    ):Toot

    @DELETE("api/v1/statuses/{id}")
    suspend fun deleteToot(
        @Header("Authorization") accessToken: String,
        @Path("id") id: String
    )
}