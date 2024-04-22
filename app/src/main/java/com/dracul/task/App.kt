package com.dracul.task

import android.app.Application
import com.dracul.task.di.DaggerInjector

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        DaggerInjector.init()
    }
}