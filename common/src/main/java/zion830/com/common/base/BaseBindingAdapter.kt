package zion830.com.common.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect
import android.net.Uri
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.internal.ViewUtils.dpToPx
import zion830.com.common.ext.filterNotNull


@BindingAdapter("visibleIf")
fun View.visibleIf(value: Boolean) {
    isVisible = value
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("bindItem")
fun RecyclerView.bindItems(items: List<Any>?) {
    val defaultList = items?.filterNotNull() ?: listOf()
    (adapter as? BaseRecyclerView<*, Any>)?.submitList(defaultList)
}

@BindingAdapter("loadImage")
fun ImageView.loadDrawableImg(drawableResId: Int) {
    Glide.with(context)
        .load(ContextCompat.getDrawable(context, drawableResId))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

@BindingAdapter("loadImage")
fun ImageView.loadUrlImg(url: String?) {
    Glide.with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

@BindingAdapter("loadImage")
fun ImageView.loadUriImg(uri: Uri?) {
    Glide.with(context)
        .load(uri)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

@SuppressLint("RestrictedApi")
@BindingAdapter("loadRoundImage")
fun ImageView.loadRoundUriImg(uri: Uri?) {
    val requestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(dpToPx(context, 8).toInt()))
    setPadding(0, 0, 0, 0)

    Glide.with(context)
        .load(uri)
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(requestOptions)
        .into(this)
}

@SuppressLint("RestrictedApi")
@BindingAdapter("loadRoundImage")
fun ImageView.loadRoundUrlImg(url: String?) {
    if (url.isNullOrBlank()) {
        return
    }

    val requestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(dpToPx(context, 8).toInt()))
    setPadding(0, 0, 0, 0)

    Glide.with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(requestOptions)
        .into(this)
}

@BindingAdapter("loadImage")
fun ImageView.loadBitmap(bitmap: Bitmap?) {
    Glide.with(context)
        .load(bitmap)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

@BindingAdapter("onSingleClick")
fun View.onSingleClick(listener: () -> Unit) {
    setOnClickListener {
        listener()
    }
}

fun Activity.getRootView(): View {
    return findViewById<View>(android.R.id.content)
}

fun Context.convertDpToPx(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        this.resources.displayMetrics
    )
}

fun Activity.isKeyboardOpen(): Boolean {
    val visibleBounds = Rect()
    this.getRootView().getWindowVisibleDisplayFrame(visibleBounds)
    val heightDiff = getRootView().height - visibleBounds.height()
    val marginOfError = Math.round(this.convertDpToPx(50F))
    return heightDiff > marginOfError
}

fun Activity.isKeyboardClosed(): Boolean {
    return !this.isKeyboardOpen()
}