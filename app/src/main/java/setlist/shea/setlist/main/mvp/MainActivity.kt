package setlist.shea.setlist.main.mvp

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import setlist.shea.domain.model.SetList
import setlist.shea.setlist.R
import setlist.shea.setlist.list.mvp.SetListFragment
import javax.inject.Inject

open class MainActivity : DaggerAppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private lateinit var toolbar : Toolbar

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.load -> { presenter?.loadSetListTitles()
                return true
            }
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        toolbar = bind(R.id.toolbar)
        setSupportActionBar(toolbar)
        lifecycle.addObserver(presenter)
    }

    override fun showList(setList : SetList?) {
        val setListFragment = SetListFragment.newInstance(setList)
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, setListFragment)
                .commit()

        toolbar.title = setList?.listName
    }

    override fun showLoadDialog(setList: List<SetList>) {
        val setListArray: Array<String?> = emptyArray()

        for (i in 0 until setList.size) {
            setListArray[i] = setList[i].listName
        }

        AlertDialog.Builder(this)
                .setTitle(getString(R.string.load_setlist))
                .setSingleChoiceItems(setListArray, -1) { _, _ -> }
                .setTitle(getString(R.string.new_setlist_dialog_title))
                .setPositiveButton(getString(R.string.ok), presenter?.getAddSetListClickListener(setListArray))
                .setNegativeButton(getString(R.string.cancel), (DialogInterface.OnClickListener
                { _, _ ->  }))
                .show()
    }
}
