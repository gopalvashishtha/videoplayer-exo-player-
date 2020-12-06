package com.example.videoplayer.adapters

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.videoplayer.adapters.adapter.MovieViewHolder
import com.example.videoplayer.models.Video
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.example.videoplayer.databinding.ItemMovieBinding


class adapter(private val videoList: List<Video?>?) : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val video = videoList!![position]
        Log.d(TAG, "onBindViewHolder: " + position);
        holder.bind(video)
        holder.binding.item.tag = position;
    }

    override fun getItemCount(): Int {
        return videoList?.size ?: 0
    }

    inner class MovieViewHolder(val binding: ItemMovieBinding) : ViewHolder(binding.root) {
        lateinit var mPlayer: SimpleExoPlayer
         var currentPos: Int = 0
        fun bind(video: Video?) {
            binding.v = video
            binding.index = adapterPosition;
            binding.executePendingBindings()
        }

        private fun buildMediaSource(uri: Uri): MediaSource {
            val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(binding.item.context, "ua")
            val extractorSourceFactory = DefaultExtractorsFactory()
            return ProgressiveMediaSource.Factory(dataSourceFactory, extractorSourceFactory).createMediaSource(uri) } }




    companion object {
        private const val TAG = "VideoAdapter"
    }

}