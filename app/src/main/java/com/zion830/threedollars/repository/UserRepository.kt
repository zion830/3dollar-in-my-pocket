package com.zion830.threedollars.repository

import com.zion830.threedollars.network.RetrofitBuilder
import com.zion830.threedollars.network.ServiceApi
import com.zion830.threedollars.repository.model.request.NewUser
import com.zion830.threedollars.repository.model.response.*
import com.zion830.threedollars.utils.SharedPrefUtils
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call

class UserRepository(
    private val service: ServiceApi = RetrofitBuilder.service
) {

    suspend fun tryLogin(nickName: String): LoginResponse? {
        val newUser = NewUser(nickName, SharedPrefUtils.getKakaoId() ?: "empty", "KAKAO")
        return RetrofitBuilder.loginService.tryLogin(newUser)
    }

    suspend fun getUserInfo(userId: Int = SharedPrefUtils.getUserId()): UserInfoResponse? = service.getUser(userId)

    suspend fun getMyReviews(
        userId: Int = SharedPrefUtils.getUserId(),
        page: Int = 1
    ): Call<MyReviewResponse> = service.getMyReview(userId, page)

    suspend fun getMyStore(
        userId: Int = SharedPrefUtils.getUserId(),
        page: Int = 1
    ): Call<MyStoreResponse> = service.getMyStore(userId, page)

    suspend fun updateName(newName: String, userId: Int = SharedPrefUtils.getUserId()): Call<Response> = service.setName(newName, userId)

    suspend fun deleteUser(): Call<ResponseBody?> = service.deleteUser()

    suspend fun loadFaqTags(): FaqTagResponse = service.getFaqTags()

    suspend fun loadFaqs(tagIds: IntArray): FaqByTagResponse = service.getFaqByTag(tagIds)
}