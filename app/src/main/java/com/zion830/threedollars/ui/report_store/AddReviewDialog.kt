package com.zion830.threedollars.ui.report_store

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.zion830.threedollars.databinding.DialogAddReviewBinding
import com.zion830.threedollars.ui.store_detail.vm.StoreDetailViewModel

class AddReviewDialog : DialogFragment() {
    private val viewModel: StoreDetailViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DialogAddReviewBinding.inflate(inflater)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.ibClose.setOnClickListener {
            viewModel.clearReview()
            dismiss()
        }
        binding.btnFinish.setOnClickListener {
            viewModel.addReview(binding.rating.rating)
            dismiss()
        }

        viewModel.addReviewResult.observe(this) {
            if (it) {
                viewModel.clearReview()
            }
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val windowManager = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = size.x
        params?.width = (deviceWidth * 0.9).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    companion object {

        fun getInstance() = AddReviewDialog()
    }
}