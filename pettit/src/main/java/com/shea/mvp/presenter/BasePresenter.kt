package com.shea.mvp.presenter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Bundle

import com.shea.mvp.interactor.BaseInteractor
import com.shea.mvp.view.BaseView


abstract class BasePresenter<I : BaseInteractor, V : BaseView<*>>(protected var interactor: I, protected var view: V) : LifecycleObserver {

    fun setupViews(savedInstanceState: Bundle?) {
        view.setupViews(savedInstanceState)
        onSetupViews(savedInstanceState)
    }

    open fun onSetupViews(savedInstanceState: Bundle?) { /*Override*/
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
        view.destroy()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
    }

    open fun onSaveState(outState: Bundle) { /* Override*/
    }
}