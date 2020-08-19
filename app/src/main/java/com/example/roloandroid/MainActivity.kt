package com.example.roloandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roloandroid.data.AppDatabase
import com.example.roloandroid.data.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}