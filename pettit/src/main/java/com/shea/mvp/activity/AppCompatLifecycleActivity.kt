package com.shea.mvp.activity

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.support.v7.app.AppCompatActivity


abstract class AppCompatLifecycleActivity : AppCompatActivity(), LifecycleRegistryOwner {
    var lifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }
}
