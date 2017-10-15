package setlist.shea.setlist.main.mvp

import android.content.DialogInterface
import android.os.Bundle
import com.shea.mvp.presenter.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import setlist.shea.domain.model.SetList
import javax.inject.Inject


/**
 * Created by Adam on 6/3/2017.
 */
class MainPresenter @Inject constructor(private var mainRepository: MainContract.Repository, private var mainView: MainContract.View) : BasePresenter<MainContract.Repository, MainContract.View>(mainRepository, mainView), MainContract.Presenter {

    override fun onSetupViews(savedInstanceState: Bundle?) {
        val setList = mainRepository.getCurrentSetList()
        mainView.showList(setList)
    }

    override fun loadSetListTitles() {
        mainRepository.getSetListTitles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { list, _ -> mainView.showLoadDialog(list) }
    }

    override fun loadSetList(setList: SetList) {
        mainRepository.setCurrentSetList(setList.listName)
        mainView.showList(setList)
    }

    override fun getAddSetListClickListener(setListArray: Array<String?>): DialogInterface.OnClickListener {
        return DialogInterface.OnClickListener { dialog, _ ->
            loadSetList(SetList(setListArray[(dialog as android.app.AlertDialog).listView.checkedItemPosition]!!)) }
    }

    override fun onSaveState(outState: Bundle) {
    }
}