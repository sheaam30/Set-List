package setlist.shea.setlist.main.mvp

import android.os.Bundle
import android.support.v7.widget.Toolbar
import dagger.android.support.DaggerAppCompatActivity
import setlist.shea.domain.model.SetList
import setlist.shea.setlist.R
import setlist.shea.setlist.setlist.SetListFragment
import javax.inject.Inject

open class MainActivity : DaggerAppCompatActivity(), MainContract.View {

    @Inject
    lateinit var mainViewModel: MainContract.Presenter

    private lateinit var toolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews(savedInstanceState)
        mainViewModel.onSetupViews(savedInstanceState)
    }

    private fun setupViews(bundle: Bundle?) {
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun showSetList(setList : SetList?) {
        val setListFragment = SetListFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, setListFragment)
                .commit()
    }
}
