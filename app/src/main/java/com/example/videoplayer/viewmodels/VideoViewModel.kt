package com.example.videoplayer.viewmodels

import android.app.Application
import android.content.Context
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.videoplayer.models.Video
import java.util.*

class VideoViewModel(application: Application) : AndroidViewModel(application) {
    var videoList: MutableLiveData<List<Video?>>? = null
        private set

    fun init() {
        if (videoList != null) {
            return
        }
        videoList = getAllVideoPath(getApplication<Application>().applicationContext)
    }

    private fun getAllVideoPath(context: Context): MutableLiveData<List<Video?>> {
        val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Video.VideoColumns.DATA)
        val cursor = context.contentResolver.query(uri, projection, null, null, null)
        val videoArrayList: ArrayList<Video?> = ArrayList<Video?>()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val video = Video()
                video.location = cursor.getString(0)
                
                videoArrayList.add(video)
            }
            cursor.close()
        }
        val data = MutableLiveData<List<Video?>>()
        data.setValue(videoArrayList)
        return data }


}