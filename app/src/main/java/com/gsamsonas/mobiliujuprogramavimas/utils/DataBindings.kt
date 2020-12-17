package com.gsamsonas.mobiliujuprogramavimas.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visibility")
fun setVisibility(view: View, visibility: Boolean?) {
    val visibilityValue = visibility?.let {
        if (visibility) View.VISIBLE else View.GONE
    } ?: View.GONE
    if (view.visibility == visibilityValue) return

    view.visibility = visibilityValue
}