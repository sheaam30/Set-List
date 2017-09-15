package setlist.shea.setlist.main

import com.shea.mvp.presenter.BasePresenter


/**
 * Created by Adam on 6/3/2017.
 */
class MainPresenter constructor(interactor: MainInteractor, view: MainInterface.MainViewInterface) : BasePresenter<MainInteractor, MainInterface.MainViewInterface>(interactor, view), MainInterface.MainPresenterInterface {

    override fun onCreate() {
        super.onCreate()
        view.showList()
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