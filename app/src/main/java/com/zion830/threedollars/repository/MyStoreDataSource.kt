package com.zion830.threedollars.repository

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.zion830.threedollars.repository.model.response.Store
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.await
import kotlin.coroutines.CoroutineContext

class MyStoreDataSource(
    private val scope: CoroutineScope,
    private val context: CoroutineContext
) : PageKeyedDataSource<Int, Store>() {

    private val repository: UserRepository = UserRepository()

    private var totalPage = 0

    class Factory(
        private val scope: CoroutineScope,
        private val context: CoroutineContext
    ) : DataSource.Factory<Int, Store>() {

        override fun create(): DataSource<Int, Store> = MyStoreDataSource(scope, context)
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Store>) {
        scope.launch(context) {
            val data = repository.getMyStore().await()
            totalPage = data.totalPages
            callback.onResult(data.store ?: listOf(), null, 2)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Store>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Store>) {
        if (params.key > totalPage) {
            return
        }

        scope.launch(context) {
            val data = repository.getMyStore(page = params.key).await()
            callback.onResult(data.store ?: listOf(), params.key + 1)
        }
    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }

    companion object {
        private const val PAGE_SIZE = 5

        val pageConfig = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPrefetchDistance(PAGE_SIZE)
            .build()
    }
}