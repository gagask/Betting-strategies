/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.bettingstrategies


import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bettingstrategies.database.Strategy

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {

        Glide.with(imgView.context)
            .load(imgUrl)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation))
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Strategy>?) {
    val adapter = recyclerView.adapter as StrategiesAdapter
    adapter.submitList(data?.toList())
}

@BindingAdapter("favorite")
fun bindImageButton(starButton: ImageButton, favorite: Boolean?) {
    Log.i("Image bind", "_")
    when(favorite){
        true -> {
            starButton.visibility = View.VISIBLE
            starButton.setBackgroundResource(android.R.drawable.btn_star_big_on)
        }
        false -> {
            starButton.visibility = View.VISIBLE
            starButton.setBackgroundResource(android.R.drawable.btn_star_big_off)
        }
        else -> {
            starButton.visibility = View.GONE
        }
    }
}

