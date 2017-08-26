package com.shea.mvp.activity

import android.os.Bundle
import com.shea.mvp.presenter.BasePresenter

abstract class BaseActivity<T : BasePresenter<*, *>> : AppCompatLifecycleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        getPresenter().setupViews(savedInstanceState)
        lifecycle.addObserver(getPresenter())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        getPresenter().onSaveState(outState)
    }

    open fun injectDependencies() { }
    protected abstract fun getPresenter() : T
    protected abstract val layoutId: Int
}
