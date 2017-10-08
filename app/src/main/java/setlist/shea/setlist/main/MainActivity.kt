package setlist.shea.setlist.main

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.shea.mvp.activity.BaseActivity
import com.shea.mvp.presenter.BaseInterface
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import setlist.shea.domain.model.SetList
import setlist.shea.setlist.R
import setlist.shea.setlist.list.SetListFragment
import setlist.shea.setlist.main.mvp.MainContract
import javax.inject.Inject

open class MainActivity : BaseActivity<MainContract.Presenter>(), HasSupportFragmentInjector, MainContract.View {


    @Inject
    lateinit var presenter: MainContract.Presenter

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun injectDependencies() {
        AndroidInjection.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.load -> { presenter.loadSetListTitles()
                return true
            }
        }
        return false
    }

    override val layoutId: Int
        get() = setlist.shea.setlist.R.layout.activity_main

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentDispatchingAndroidInjector
    }

    private lateinit var toolbar : Toolbar

    override fun onSetupViews(savedInstanceState: Bundle?) {
        toolbar = bind(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun showList(setList : SetList?) {
        val setListFragment = SetListFragment.newInstance(setList)
        getFragmentManager().beginTransaction()
                .replace(R.id.content, setListFragment)
                .commit()

        toolbar.title = setList?.listName
    }

    override fun showLoadDialog(setList: List<SetList>) {
        val setListArray: Array<String?> = emptyArray()

        for (i in 0 until setList.size) {
            setListArray[i] = setList[i].listName
        }

        dialogBuilder
                .setTitle(context.getString(R.string.load_setlist))
                .setSingleChoiceItems(setListArray, -1) { _, _ -> }
                .setTitle(context.getString(R.string.new_setlist_dialog_title))
                .setPositiveButton(context.getString(R.string.ok), presenter?.getAddSetListClickListener(setListArray))
                .setNegativeButton(context.getString(R.string.cancel), (DialogInterface.OnClickListener
                { _, _ ->  }))
                .show()
    }
}
