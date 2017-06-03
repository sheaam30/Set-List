package com.shea.mvp.activity

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.shea.mvp.presenter.BasePresenter

abstract class BaseActivity<T : BasePresenter<*, *>> : AppCompatLifecycleActivity() {

    protected lateinit var presenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        createPresenter(savedInstanceState)
        presenter.setupViews(savedInstanceState)
        lifecycle.addObserver(presenter)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSaveState(outState)
    }

    protected abstract fun createPresenter(restoredBundle: Bundle?)
    protected abstract val layoutId: Int
}
