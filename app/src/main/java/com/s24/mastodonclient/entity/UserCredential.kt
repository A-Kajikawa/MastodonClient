package com.s24.mastodonclient.entity

data class UserCredential(
    val instanceUrl: String,
    val username: String? = null,
    val accessToken: String? = null
)
