package setlist.shea.setlist.main.mvp

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.widget.Toolbar
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

        toolbar.title = setList?.listName
    }

    override fun showLoadDialog(setList: List<SetList>) {
        var chosen = -1
        val setListArray: Array<String?> = arrayOfNulls(setList.size)

        for (i in 0 until setList.size) {
            setListArray[i] = setList[i].listName
        }

        dialogBuilder
                .setTitle(context.getString(R.string.load_setlist))
                .setSingleChoiceItems(setListArray, -1) { dialog, item ->
                    chosen = item
                }
                .setTitle(context.getString(R.string.new_setlist_dialog_title))
                .setPositiveButton(context.getString(R.string.ok), { dialog, item ->
                    presenterInterface?.loadSetList(SetList(setListArray[chosen].toString()))
                })
                .setNegativeButton(context.getString(R.string.cancel), (DialogInterface.OnClickListener
                { _, _ ->  }))
                .show()
    }
}