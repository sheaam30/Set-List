package setlist.shea.setlist.list

import android.os.Bundle
import com.shea.mvp.fragment.BaseFragment
import dagger.android.support.AndroidSupportInjection
import setlist.shea.domain.model.SetList
import setlist.shea.setlist.R
import setlist.shea.setlist.list.mvp.SetListContract
import javax.inject.Inject



/**
 * Created by Adam on 8/28/2017.
 */
class SetListFragment : BaseFragment<SetListContract.Presenter>() {

    @Inject
    lateinit var setPresenterContract: SetListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val setListTitle = arguments?.get(SONGS_KEY)
        if (setListTitle != null && (setListTitle as String).isNotEmpty()) {
            setPresenterContract.loadSongsFromSetList(SetList(setListTitle))
        }
    }


    override val layoutId: Int
        get() = R.layout.fragment_list

    override fun injectDependencies() {
        AndroidSupportInjection.inject(this)
    }

    companion object {
        val SONGS_KEY = "songs"

        /**
         * new instance pattern for fragment
         */
        @JvmStatic
        fun newInstance(setList : SetList?): SetListFragment {
            val newsFragment = SetListFragment()
            val args = Bundle()
            args.putString(SONGS_KEY, setList?.toString())
            newsFragment.arguments = args
            return newsFragment
        }
    }

    override fun getPresenter(): SetListContract.Presenter {
        return setPresenterContract
    }
}