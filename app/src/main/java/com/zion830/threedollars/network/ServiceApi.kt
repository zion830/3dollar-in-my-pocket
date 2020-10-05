package com.zion830.threedollars.network

import retrofit2.http.*

interface ServiceApi {

    // 카테고리
    @GET("/api/v1/category/distance")
    fun getCategoryByDistance(
        @Query("category") category: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    )

    // 리뷰
    @GET("/api/v1/review/save")
    fun getReview(
        @Query("page") page: Int,
        @Query("userId") userId: Int
    )

    @POST("/api/v1/review/review")
    fun saveReview(
        @Query("storeId") storeId: Int,
        @Query("userId") userId: Int
    )

    // 가게
    @DELETE("/api/v1/store/delete")
    fun deleteStore()

    @GET("/api/v1/store/detail")
    fun getStoreDetail()

    @GET("/api/v1/store/get")
    fun getAllStore()

    @POST("/api/v1/store/save")
    fun saveStore()

    @PUT("/api/v1/store/update")
    fun updateStore()

    @GET("/api/v1/store/user")
    fun getUserStore()

    // 사용자
    @GET("/api/v1/user/info")
    fun getUser(@Query("userId") userId: Int)

    @POST("/api/v1/user/login")
    fun startLogin(@Query("userId") userId: Int)

    @POST("/api/v1/user/nickname")
    fun setName(
        @Query("nickName") name: String,
        @Query("userId") userId: Int
    )
}