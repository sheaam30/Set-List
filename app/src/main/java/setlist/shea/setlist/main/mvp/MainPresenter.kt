package setlist.shea.setlist.main.mvp

import android.content.DialogInterface
import android.os.Bundle
import com.shea.mvp.presenter.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import setlist.shea.domain.model.SetList


/**
 * Created by Adam on 6/3/2017.
 */
class MainPresenter constructor(var mainRepository: MainContract.Repository, var mainView: MainContract.View) : Presenter<MainContract.Repository, MainContract.View>(mainRepository, mainView), MainContract.Presenter {

    override fun onSetupViews(savedInstanceState: Bundle?) {
        val setList = mainRepository.getCurrentSetList()
        mainView.showList(setList)
    }

    override fun loadSetListTitles() {
        mainRepository.getSetListTitles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t1, _ -> mainView.showLoadDialog(t1) }
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //
//    private fun getShareFileIntent(): Intent {
//
//        /*
//                 * Get a File for the selected file name.
//                 * Assume that the file names are in the
//                 * mImageFilename array.
//                 */
//        val requestFile = File(mImageFilename[position])
//        /*
//                 * Most file-related method calls need to be in
//                 * try-catch blocks.
//                 */
//        // Use the FileProvider to get a content URI
//        try {
//            fileUri = FileProvider.getUriForFile(
//                    this@MainActivity,
//                    "com.example.myapp.fileprovider",
//                    requestFile)
//        } catch (e: IllegalArgumentException) {
//            Log.e("File Selector",
//                    "The selected file can't be shared: " + clickedFilename)
//        }
//
//        val u1 = Uri.fromFile(file)
//
//
//        val sendIntent = Intent(Intent.ACTION_SEND)
//        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Person Details")
//        sendIntent.putExtra(Intent.EXTRA_STREAM, u1)
//        sendIntent.type = "text/richtext"
//    }

}