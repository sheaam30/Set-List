package com.shea.mvp.fragment

import android.arch.lifecycle.LifecycleFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shea.mvp.presenter.BasePresenter

abstract class BaseFragment<T : BasePresenter<*, *>> :  LifecycleFragment() {

    protected var presenter: T? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createPresenter(savedInstanceState)
        presenter!!.setupViews(savedInstanceState!!)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter!!.onSaveState(outState)
    }

    protected abstract fun createPresenter(restoredBundle: Bundle?)
    protected abstract val layoutId: Int

}