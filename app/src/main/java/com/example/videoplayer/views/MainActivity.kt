package com.example.videoplayer.views

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.LinearLayout.HORIZONTAL
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.*
import com.example.videoplayer.R
import com.example.videoplayer.adapters.adapter
import com.example.videoplayer.databinding.ActivityMainBinding
import com.example.videoplayer.viewmodels.VideoViewModel

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var mVideoViewModel: VideoViewModel? = null
    var adapter: adapter? = null
    var recyclerView: RecyclerView? = null
    private val request_code = 101


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupPermissions()
        setupObservers()
        setupRecyclerView()


        mVideoViewModel!!.videoList!!.observe(this, Observer { adapter!!.notifyDataSetChanged() })

    }

    private fun setupObservers(){
        mVideoViewModel = ViewModelProviders.of(this).get(VideoViewModel::class.java)//here we are connecting our main activity with viewmodel activity
        mVideoViewModel!!.init()
    }

    private fun setupRecyclerView() {
        recyclerView = binding!!.list
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager =      LinearLayoutManager(this)
        val snapHelper: SnapHelper =  PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView!!)
        adapter = adapter(mVideoViewModel!!.videoList!!.value)
        recyclerView!!.adapter = adapter

    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            request_code)
    }

    override fun onRequestPermissionsResult
                (requestCode: Int,permissions: Array<String>, grantResults: IntArray)
    {
        when (requestCode) {
        request_code -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "Please allow the storage permission to access the files", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission granted successfully, please restart the app if using for the first time", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}