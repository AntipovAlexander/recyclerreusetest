package com.android.coroutinesplayground

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import androidx.core.widget.toast
import com.android.coroutinesplayground.data.retrofit.RetrofitFactory
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            val deffered = RetrofitFactory.makeRetrofitService().getPosts()
            val data = deffered.await()
        }
    }
}
