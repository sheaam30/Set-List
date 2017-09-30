package setlist.shea.setlist.main

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.ListView
import com.shea.mvp.activity.BaseActivity
import com.shea.mvp.view.BaseView
import setlist.shea.domain.model.SetList
import setlist.shea.setlist.R
import setlist.shea.setlist.list.SetListFragment

/**
 * Created by Adam on 6/3/2017.
 */
open class MainView(activity: BaseActivity<*>?) : BaseView<MainInterface.MainPresenterInterface>(activity), MainInterface.MainViewInterface {

    lateinit var toolbar : Toolbar

    override fun onSetupViews(savedInstanceState: Bundle?) {
        toolbar = bind(R.id.toolbar)
        setSupportActionbar(toolbar)
    }

    override fun showList(setList : SetList?) {
        val setListFragment = SetListFragment.newInstance(setList)
        getFragmentManager().beginTransaction()
                .replace(R.id.content, setListFragment)
                .commit()
    }

    override fun showLoadDialog(setList: List<SetList>) {
        dialogBuilder
                .setTitle("Load SetList")

        var setListArray: Array<String?> = arrayOfNulls(setList.size)

        for (i in 0 until setList.size) {
            setListArray[i] = setList[i].listName
        }

        dialogBuilder
                .setSingleChoiceItems(setListArray, 0, { dialogInterface, i -> })
        dialogBuilder
                .setPositiveButton(context.getText(R.string.ok),
                        { dialogInterface, i ->
                            presenterInterface?.loadSetList(setList.get((dialogInterface as ListView).checkedItemPosition)) })
                .setNegativeButton(context.getText(R.string.cancel),
                        { _, _ ->  })
        dialogBuilder.show()
    }

    fun test(): Array<String> {
        val elems = arrayListOf<String>()
        return elems.toTypedArray()
    }
}