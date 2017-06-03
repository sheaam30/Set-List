package com.shea.mvp.activity

import android.support.v7.app.AppCompatActivity
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.LifecycleRegistry



abstract class AppCompatLifecycleActivity : AppCompatActivity(), LifecycleRegistryOwner {
    var lifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }
}
