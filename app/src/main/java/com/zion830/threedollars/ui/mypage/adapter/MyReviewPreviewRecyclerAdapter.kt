package com.zion830.threedollars.ui.mypage.adapter

import com.zion830.threedollars.R
import com.zion830.threedollars.databinding.ItemMyReviewPreviewBinding
import com.zion830.threedollars.repository.model.response.Review
import zion830.com.common.base.BaseRecyclerView
import zion830.com.common.listener.OnItemClickListener

class MyReviewPreviewRecyclerAdapter(
    reviewClickEvent: OnItemClickListener<Review>
) : BaseRecyclerView<ItemMyReviewPreviewBinding, Review>(R.layout.item_my_review_preview) {

    init {
        clickListener = reviewClickEvent
    }

    override fun submitList(list: MutableList<Review>?) {
        super.submitList(list?.take(PREVIEW_COUNT))
    }

    companion object {
        private const val PREVIEW_COUNT = 3
    }
}