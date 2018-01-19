package setlist.shea.setlist.main.mvp

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.shea.mvp.activity.BaseActivity
import setlist.shea.domain.model.SetList
import setlist.shea.setlist.R
import setlist.shea.setlist.set_list.SetListFragment
import javax.inject.Inject

open class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View {

    @Inject
    lateinit var mainPresenter: MainContract.Presenter

    override val layoutId: Int
        get() = setlist.shea.setlist.R.layout.activity_main

    private lateinit var toolbar : Toolbar

    override fun setupViews(bundle: Bundle?) {
        toolbar = bind(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun getPresenter(): MainContract.Presenter {
        return mainPresenter
    }

    override fun showSetList(setList : SetList?) {
        val setListFragment = SetListFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, setListFragment)
                .commit()
    }

    override fun showLoadDialog(setList: List<SetList>) {
        val setListArray: Array<String?> = arrayOfNulls(setList.size)

        for (i in 0 until setList.size) {
            setListArray[i] = setList[i].listName
        }

        AlertDialog.Builder(this)
                .setTitle(getString(R.string.load_setlist))
                .setSingleChoiceItems(setListArray, -1, { _, _ -> })
                .setTitle(getString(R.string.new_setlist_dialog_title))
                .setPositiveButton(getString(R.string.ok), mainPresenter.getAddSetListClickListener(setListArray))
                .setNegativeButton(getString(R.string.cancel), (DialogInterface.OnClickListener( {_, _ -> })))
                .show()
    }
}
