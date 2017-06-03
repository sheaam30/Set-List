package com.shea.mvp.view

import android.app.Activity
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.res.Resources
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.view.View

import com.shea.mvp.activity.BaseActivity
import com.shea.mvp.presenter.BasePresenterInterface

import butterknife.ButterKnife
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


abstract class BaseView<T : BasePresenterInterface>(//Lock down this Activity so nobody does anything mischievous with it.
        open var activity: BaseActivity<*>?) {

    protected var presenter: T? = null

    fun destroy() {
        activity = null
    }

    fun attach(presenter: T) {
        this.presenter = presenter
    }

    fun detach() {
        this.presenter = null
    }


    fun <T : View> bind(@IdRes res : Int) : T {
        @Suppress("UNCHECKED_CAST")
        return activity!!.bind(res)
    }


    fun setupViews(savedInstanceState: Bundle?) {
        ButterKnife.bind(this, activity!!)
        onSetupViews(savedInstanceState)
    }

    private fun viewNotFound(id:Int, desc: KProperty<*>): Nothing =
            throw IllegalStateException("View ID $id for '${desc.name}' not found.")


    protected fun setSupportActionbar(toolbar: Toolbar) {
        activity?.setSupportActionBar(toolbar)
    }

    /**
     * Provide subclass with a AlertDialog Builder
     * so they can create their own AlertDialog
     */
    protected val dialogBuilder: AlertDialog.Builder
        get() = AlertDialog.Builder(activity!!)

    protected val resources: Resources
        get() = activity!!.resources

    abstract fun onSetupViews(savedInstanceState: Bundle?)

    fun <T : View> Activity.bind(@IdRes res : Int) : T {
        @Suppress("UNCHECKED_CAST")
        return findViewById(res) as T
    }
}
