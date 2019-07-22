package com.xurxodev.movieskotlinkata.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso


fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(this.context).inflate(layoutRes, this, false)
}

fun ImageView.loadUrl (url:String){
    try {
        Picasso.get().isLoggingEnabled = true
        Picasso.get().load(url).into(this)
    }
    catch (e: Exception){
        Picasso.get().isLoggingEnabled = true
    }
}