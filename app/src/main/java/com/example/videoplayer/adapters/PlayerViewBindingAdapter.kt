package com.example.videoplayer.adapters

import android.net.Uri
import androidx.databinding.BindingAdapter
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

class PlayerViewBindingAdapter {

    companion object {

        @JvmStatic
        @BindingAdapter(value = ["url", "index"], requireAll = false)
        fun PlayerView.playVideo(url: String, index: Int) {
            val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(context, "ua")
            val extractorSourceFactory = DefaultExtractorsFactory()
            val mPlayer = SimpleExoPlayer.Builder(context, DefaultRenderersFactory(context)).build()
            this.player = mPlayer;
            mPlayer.playWhenReady = false
            val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory, extractorSourceFactory).createMediaSource(Uri.parse(url));
            mPlayer.prepare(mediaSource) }
                                                }}



